package com.alan.interceptor;

import com.alan.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {
    //检查当前会话是否有User，有则放行，没有不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("SESSION_USER");
        if(user == null){
            return false;
        }
        if(user instanceof User){
            User u = (User)user;
            //u.setPassword(null);
            request.getSession().setAttribute("SESSION_USER",u);
            return true;
        }else{
            System.out.println("请不要搞事");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
