package com.alan.util;

import javax.servlet.http.HttpServletRequest;

public class CaptchaUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected = ((String)request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY)).toUpperCase();
        String verifyCodeActual = request.getParameter("verify").toUpperCase();
        if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)){
            return false;
        }
        return true;
    }
}