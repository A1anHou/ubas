package com.alan.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alan.model.Login;
import com.alan.util.ResponseData;
import com.alan.util.TokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class UserTokenInterceptor implements HandlerInterceptor{

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception arg3)
            throws Exception {
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView model) throws Exception {
    }

    //拦截每个请求
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uri = requestURI.substring(requestURI.lastIndexOf("/"));
        //排除登录请求
        if(uri.startsWith("/login") ) {
            return true;
        }
        //排除注册请求
        if(uri.startsWith("/register") ) {
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getParameter("token");
        System.err.println("token:"+token);
        ResponseData responseData = ResponseData.ok();
        //token不存在
        if(null != token) {
            //验证Token
            String loginId = request.getParameter("userId");
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");
            RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
            String storedToken = (String) redisTemplate.opsForValue().get(loginId);
            //验证Token，若Redis中不存在以该用户名为key的token或传来的token与Redis中存储的token不一致则不放行
            if(storedToken==null){
                responseData = ResponseData.forbidden();
                responseMessage(response, response.getWriter(), responseData);
                return false;
            }
            if(!token.equals(storedToken)){
                responseData = ResponseData.forbidden();
                responseMessage(response, response.getWriter(), responseData);
                return false;
            }
            Login login = TokenUtil.unsign(token, Login.class);
            //解密token后的loginId与用户传来的loginId不一致，一般都是token过期
            System.err.println("解密ID"+login.getUserId());
            System.err.println("传来的ID"+loginId);
            if(null != loginId && null != login) {
                if(Integer.parseInt(loginId) == login.getUserId()) {
                    return true;
                } else {
                    responseData = ResponseData.forbidden();
                    responseMessage(response, response.getWriter(), responseData);
                    return false;
                }
            } else {
                responseData = ResponseData.forbidden();
                responseMessage(response, response.getWriter(), responseData);
                return false;
            }
        } else {
            System.err.println("Token不存在");
            responseData = ResponseData.forbidden();
            responseMessage(response, response.getWriter(), responseData);
            return false;
        }
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) throws JsonProcessingException {
        responseData = ResponseData.forbidden();
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }

}