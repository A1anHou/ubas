package com.alan.controller;


import com.alan.model.Admin;
import com.alan.service.AdminService;
import com.alan.util.CaptchaUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by AlanHou on 2019/4/14.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger log = Logger.getLogger(AdminController.class);
    @Autowired
    AdminService adminService;
    @RequestMapping("/login")
    public String login(Model model,HttpServletRequest request, HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(!CaptchaUtil.checkVerifyCode(request)){
            model.addAttribute("login_result","验证码错误");
            return "login";
        }else{
            String adminTel = request.getParameter("username");
            String adminPwd = request.getParameter("password");
            if(adminTel=="" || adminPwd==""){
                model.addAttribute("login_result","用户名和密码不能为空");
                return "login";
            }
            //数据库检查
            Admin admin = adminService.queryAdminByTel(Long.parseLong(adminTel));
            if(admin != null){
                if(admin.getAdminPwd().equals(adminPwd)){
                    //将用户信息存储到Session中
                    session.setAttribute("SESSION_USER",admin);
                    model.addAttribute("login_result","登录成功");
                    return "admin/index";
                }
            }
            model.addAttribute("login_result","用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/girl")
    public String girl(Model model){
        model.addAttribute("girl","菲菲");
        return "";
    }
}
