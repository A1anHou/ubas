package com.alan.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MethodRuntimeCountInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = Logger.getLogger(MethodRuntimeCountInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //定义开始时间
        long methodStart = System.currentTimeMillis();
        //将其存到请求域中
        request.setAttribute("methodStart",methodStart);
        LOGGER.info(request.getRequestURI()+"请求到达");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //取出start
        long methodStart = (long)request.getAttribute("methodStart");
        //得到end
        long methodEnd = System.currentTimeMillis();
        //计算耗时
        long spendTime = methodEnd-methodStart;
        LOGGER.info("方法耗时："+spendTime+"毫秒");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
