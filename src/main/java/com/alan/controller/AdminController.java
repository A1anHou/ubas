package com.alan.controller;


import com.alan.model.Admin;
import com.alan.model.App;
import com.alan.model.Parent;
import com.alan.model.User;
import com.alan.service.*;
import com.alan.util.CaptchaUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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
    @Autowired
    RelationService relationService;

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
        Map<Integer,Integer> parentNum = new HashMap<Integer, Integer>();
        for(User user : userList){
            parentNum.put(user.getUserId(),relationService.getParentNum(user.getUserId()));
        }
        model.addAttribute("userList",userList);
        model.addAttribute("parentNum",parentNum);
        return "admin/user";
    }

    @RequestMapping("/parent")
    public String manageParent(Model model){
        List<Parent> parentList = parentService.getAllParent();
        Map<Integer,Integer> childNum = new HashMap<Integer, Integer>();
        for(Parent parent : parentList){
            childNum.put(parent.getParentId(),relationService.getChildNum(parent.getParentId()));
        }
        model.addAttribute("parentList",parentList);
        model.addAttribute("childNum",childNum);
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

    @RequestMapping("/editAdmin")
    @ResponseBody
    public Map<String,String> editAdmin(HttpServletRequest request){
        Map<String,String> res = new HashMap<>();
        int editType = Integer.parseInt(request.getParameter("editType"));
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        if(editType==0){//修改电话
            long adminTel = Long.parseLong(request.getParameter("adminTel"));
            if(adminService.getAdminByTel(adminTel)!=null){
                res.put("status","2");
                res.put("message","该手机号已存在");
                return res;
            }else{
                adminService.editAdminTel(adminId,adminTel);
                res.put("status","1");
                res.put("message","修改成功");
                return res;
            }
        }else if(editType==1){//修改密码
            String oldPwd =request.getParameter("oldPwd");
            oldPwd = DigestUtils.md5Hex(oldPwd);
            if(oldPwd.equals(adminService.getAdminById(adminId).getAdminPwd())){
                String adminPwd = request.getParameter("adminPwd");
                adminPwd = DigestUtils.md5Hex(adminPwd);
                adminService.editAdminPwd(adminId,adminPwd);
                res.put("status","1");
                res.put("message","修改成功");
                return res;
            }else{
                res.put("status","2");
                res.put("message","原密码错误");
                return res;
            }
        }
        res.put("status","2");
        res.put("message","出现错误，请稍后再试");
        return res;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("SESSION_USER",null);
        return "login";
    }

    @RequestMapping("/delAdmin")
    public String delAdmin(HttpServletRequest request){
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        HttpSession session = request.getSession();
        if(adminId==((Admin)session.getAttribute("SESSION_USER")).getAdminId()){
            adminService.delAdmin(adminId);
            session.setAttribute("SESSION_USER",null);
            return "login";
        }else{
            adminService.delAdmin(adminId);
            return "forward:/admin/admin";
        }
    }

    @RequestMapping("/delApp")
    public String delApp(@RequestParam int appId){
        appService.delApp(appId);
        return "forward:/admin/app";
    }

    @RequestMapping("/addAdmin")
    @ResponseBody
    public Map<String,String> addAdmin(@RequestParam long adminTel,@RequestParam String adminName,
                           @RequestParam String adminPwd){
        Map<String,String> res = new HashMap<>();
        if(adminService.getAdminByTel(adminTel)!=null){
            res.put("status","2");
            res.put("message","该手机号已存在");
            return res;
        }else{
            Date adminRegTime = new Date();
            adminPwd=DigestUtils.md5Hex(adminPwd);
            adminService.addAdmin(adminTel,adminName,adminPwd,adminRegTime);
            res.put("status","1");
            res.put("message","添加成功");
            return res;
        }
    }

    @RequestMapping("/editApp")
    @ResponseBody
    public Map<String,String> editApp(@RequestParam int appId,@RequestParam String appType){
        Map<String,String> res = new HashMap<>();
        appService.editAppType(appId,appType);
        res.put("status","1");
        res.put("message","添加成功");
        return res;
    }
    @RequestMapping("/searchApp")
    public String searchApp(@RequestParam String keyword, Model model){
        List<App> appList = appService.getAppByNameOrType(keyword);
        model.addAttribute("appList",appList);
        return "admin/app";
    }

    @RequestMapping("/searchUser")
    public String searchUser(@RequestParam String keyword, Model model){
        List<User> userList = userService.getUserByIdOrName(keyword);
        Map<Integer,Integer> parentNum = new HashMap<Integer, Integer>();
        for(User user : userList){
            parentNum.put(user.getUserId(),relationService.getParentNum(user.getUserId()));
        }
        model.addAttribute("userList",userList);
        model.addAttribute("parentNum",parentNum);
        return "admin/user";
    }

    @RequestMapping("/searchParent")
    public String searchParent(@RequestParam String keyword, Model model){
        List<Parent> parentList = parentService.getParentByIdOrName(keyword);
        Map<Integer,Integer> childNum = new HashMap<Integer, Integer>();
        for(Parent parent : parentList){
            childNum.put(parent.getParentId(),relationService.getChildNum(parent.getParentId()));
        }
        model.addAttribute("parentList",parentList);
        model.addAttribute("childNum",childNum);
        return "admin/parent";
    }

    @RequestMapping("/searchAdmin")
    public String searchAdmin(@RequestParam String keyword, Model model){
        List<Admin> adminList = adminService.getAdminByIdOrName(keyword);
        model.addAttribute("adminList",adminList);
        return "admin/admin";
    }

}
