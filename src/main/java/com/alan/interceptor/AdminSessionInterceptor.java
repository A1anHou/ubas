package com.alan.interceptor;

import com.alan.model.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminSessionInterceptor implements HandlerInterceptor {
    //检查当前会话是否有User，有则放行，没有不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("SESSION_USER");
        String requestURI = request.getRequestURI();
        String uri = requestURI.substring(requestURI.lastIndexOf("/"));
        //排除登录请求
        if(uri.startsWith("/login") ) {
            return true;
        }
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return false;
        }
        if(user instanceof Admin){
            Admin admin = (Admin)user;
            admin.setAdminPwd(null);
            request.getSession().setAttribute("SESSION_USER",admin);
            return true;
        }else{
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
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
