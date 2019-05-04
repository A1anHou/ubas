package com.alan.interceptor;

import com.alan.model.Parent;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ParentSessionInterceptor implements HandlerInterceptor {
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
            response.sendRedirect(request.getContextPath() + "/jsp/parent/login.jsp");
            return false;
        }
        if(user instanceof Parent){
            Parent parent = (Parent)user;
            parent.setParentPwd(null);
            request.getSession().setAttribute("SESSION_USER",parent);
            return true;
        }else{
            response.sendRedirect(request.getContextPath() + "/jsp/parent/login.jsp");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
