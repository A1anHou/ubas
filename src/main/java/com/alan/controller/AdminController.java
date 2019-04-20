package com.alan.controller;


import com.alan.model.Admin;
import com.alan.model.App;
import com.alan.model.Parent;
import com.alan.model.User;
import com.alan.service.AdminService;
import com.alan.service.AppService;
import com.alan.service.ParentService;
import com.alan.service.UserService;
import com.alan.util.CaptchaUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    AppService appService;
    @Autowired
    UserService userService;
    @Autowired
    ParentService parentService;

    @RequestMapping("/login")
    public String login(Model model,HttpServletRequest request, HttpSession session){
        Map<String,String> result = new HashMap<>();
        if(!CaptchaUtil.checkVerifyCode(request)){
            model.addAttribute("login_result","验证码错误");
            return "login";
        }else{
            String adminTel = request.getParameter("username");
            //对密码进行MD5加密
            String adminPwd = DigestUtils.md5Hex(request.getParameter("password"));

            if(adminTel=="" || adminPwd==""){
                model.addAttribute("login_result","用户名和密码不能为空");
                return "login";
            }
            //数据库检查
            Admin admin = adminService.getAdminByTel(Long.parseLong(adminTel));
            if(admin != null){
                if(admin.getAdminPwd().equals(adminPwd)){
                    //将用户信息存储到Session中
                    session.setAttribute("SESSION_USER",admin);
                    model.addAttribute("login_result","登录成功");
                    return "forward:/admin/index";
                }
            }
            model.addAttribute("login_result","用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("girl","菲菲");
        return "admin/index";
    }

    @RequestMapping("/app")
    public String manageApp(Model model){
        List<App> appList = appService.getAllApp();
        model.addAttribute("appList",appList);
        return "admin/app";
    }

    @RequestMapping("/user")
    public String manageUser(Model model){
        //log.info("查询所有用户信息");
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList",userList);
        return "admin/user";
    }

    @RequestMapping("/parent")
    public String manageParent(Model model){
        List<Parent> parentList = parentService.getAllParent();
        model.addAttribute("parentList",parentList);
        return "admin/parent";
    }

    @RequestMapping("/admin")
    public String manageAdmin(Model model){
        List<Admin> adminList = adminService.getAllAdmin();
        model.addAttribute("adminList",adminList);
        return "admin/admin";
    }

    @RequestMapping("/myInfo")
    public String myInfo(Model model,HttpSession session){
        int adminId = ((Admin)session.getAttribute("SESSION_USER")).getAdminId();
        Admin admin = adminService.getAdminById(adminId);
        model.addAttribute("admin",admin);
        return "admin/myInfo";
    }

    @RequestMapping("/editMyInfo")
    public String editMyInfo(HttpSession session,Model model){
        Admin admin = (Admin)session.getAttribute("SESSION_USER");
        model.addAttribute("admin",admin);
        return "admin/editMyInfo";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("SESSION_USER",null);
        return "login";
    }

    @RequestMapping("/delAdmin")
    public String delAdmin(){
        return null;
    }


}
