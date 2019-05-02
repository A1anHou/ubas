package com.alan.controller;


import com.alan.model.*;
import com.alan.service.*;
import com.alan.util.CaptchaUtil;
import com.alan.util.DateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
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
    public String login(Model model, HttpServletRequest request, HttpSession session) {
        Map<String, String> result = new HashMap<>();
        if (!CaptchaUtil.checkVerifyCode(request)) {
            model.addAttribute("login_result", "验证码错误");
            return "admin/login";
        } else {
            String adminTel = request.getParameter("username");
            //对密码进行MD5加密
            String adminPwd = DigestUtils.md5Hex(request.getParameter("password"));

            if (adminTel == "" || adminPwd == "") {
                model.addAttribute("login_result", "用户名和密码不能为空");
                return "admin/login";
            }
            //数据库检查
            Admin admin = adminService.getAdminByTel(Long.parseLong(adminTel));
            if (admin != null) {
                if (admin.getAdminPwd().equals(adminPwd)) {
                    //将用户信息存储到Session中
                    session.setAttribute("SESSION_USER", admin);
                    model.addAttribute("login_result", "登录成功");
                    return "forward:/admin/index";
                }
            }
            model.addAttribute("login_result", "用户名或密码错误");
            return "admin/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("SESSION_USER", null);
        return "admin/login";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        List<User> userList = userService.getRecentUser(5);
        List<App> appList = appService.getRecentApp(5);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 1900;
        Date lastWeek[] = new Date[7];
        int userNum[] = new int[7];
        int appNum[] = new int[7];
        for (int i = 0; i < 7; i++) {
            Date date = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * (6 - i));
            lastWeek[i] = DateUtil.getNeedTime(date, 0, 0, 0, 0);
        }
        for (int i = 0; i < 7; i++) {
            userNum[i] = userService.getUserNumByDate(lastWeek[i], DateUtil.getNeedTime(lastWeek[i], 23, 59, 59, 0));
            appNum[i] = appService.getAppNumByDate(lastWeek[i], DateUtil.getNeedTime(lastWeek[i], 23, 59, 59, 0));
        }
        SimpleDateFormat sp = new SimpleDateFormat("MM/dd");
        String lastSevenDays = "[";
        String newUser = "[";
        String newApp = "[";
        for (int i = 0; i < 7; i++) {
            lastSevenDays += "'" + sp.format(lastWeek[i]) + "',";
            newUser += userNum[i] + ",";
            newApp += appNum[i] + ",";
        }
        lastSevenDays += "]";
        newUser += "]";
        newApp += "]";
        model.addAttribute("userList", userList);
        model.addAttribute("appList", appList);
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("lastWeek", lastSevenDays);
        model.addAttribute("newUser", newUser);
        model.addAttribute("newApp", newApp);
        return "admin/index";
    }

    @RequestMapping(value = "/app")
    public String manageApp(Model model, Pager pager) {
        Page<?> page = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<App> appList = appService.getAllApp();
        model.addAttribute("appList", appList);
        model.addAttribute("pager", pager.getPage(page));
        return "admin/app";
    }

    @RequestMapping("/user")
    public String manageUser(Model model, Pager pager) {
        Page<?> page = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<User> userList = userService.getAllUser();
        Map<Integer, Integer> parentNum = new HashMap<Integer, Integer>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 1900;
        for (User user : userList) {
            parentNum.put(user.getUserId(), relationService.getParentNum(user.getUserId()));
        }
        model.addAttribute("userList", userList);
        model.addAttribute("parentNum", parentNum);
        model.addAttribute("pager", pager.getPage(page));
        model.addAttribute("currentYear", currentYear);
        return "admin/user";
    }

    @RequestMapping("/parent")
    public String manageParent(Model model, Pager pager) {
        Page<?> page = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Parent> parentList = parentService.getAllParent();
        Map<Integer, Integer> childNum = new HashMap<Integer, Integer>();
        for (Parent parent : parentList) {
            childNum.put(parent.getParentId(), relationService.getChildNum(parent.getParentId()));
        }
        model.addAttribute("parentList", parentList);
        model.addAttribute("childNum", childNum);
        model.addAttribute("pager", pager.getPage(page));
        return "admin/parent";
    }

    @RequestMapping("/admin")
    public String manageAdmin(Model model, Pager pager) {
        Page<?> page = PageHelper.startPage(pager.getPageNum(), pager.getPageSize());
        List<Admin> adminList = adminService.getAllAdmin();
        model.addAttribute("adminList", adminList);
        model.addAttribute("pager", pager.getPage(page));
        return "admin/admin";
    }

    //增
    @RequestMapping("/addAdmin")
    @ResponseBody
    public Map<String, String> addAdmin(@RequestParam long adminTel, @RequestParam String adminName,
                                        @RequestParam String adminPwd,HttpSession session) {
        Map<String, String> res = new HashMap<>();
        if (adminService.getAdminByTel(adminTel) != null) {
            res.put("status", "2");
            res.put("message", "该手机号已存在");
            return res;
        } else {
            Date adminRegTime = new Date();
            adminPwd = DigestUtils.md5Hex(adminPwd);
            adminService.addAdmin(adminTel, adminName, adminPwd, adminRegTime);
            Admin admin = adminService.getAdminByTel(adminTel);
            Admin optAdmin = (Admin)session.getAttribute("SESSION_USER");
            adminService.recordEdit(admin.getAdminId(), "add_admin", null, null,optAdmin.getAdminId(),new Date());
            res.put("status", "1");
            res.put("message", "添加成功");
            return res;
        }
    }

    //删
    @RequestMapping("/delApp")
    public String delApp(@RequestParam int appId, HttpSession session) {
        Admin optAdmin = (Admin)session.getAttribute("SESSION_USER");
        appService.delApp(appId);
        appService.recordEdit(appId,"del_app",null,null,optAdmin.getAdminId(),new Date());
        return "forward:/admin/app";
    }

    @RequestMapping("/delAdmin")
    public String delAdmin(HttpServletRequest request) {
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        HttpSession session = request.getSession();
        Admin optAdmin = (Admin) session.getAttribute("SESSION_USER");
        if (adminId == optAdmin.getAdminId()) {
            adminService.delAdmin(adminId);
            adminService.recordEdit(adminId, "del_admin", null, null,optAdmin.getAdminId(),new Date());
            session.setAttribute("SESSION_USER", null);
            return "admin/login";
        } else {
            adminService.delAdmin(adminId);
            adminService.recordEdit(adminId, "del_admin", null, null,optAdmin.getAdminId(),new Date());
            return "forward:/admin/admin";
        }
    }

    //改
    @RequestMapping("/editMyInfo")
    public String editMyInfo(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("SESSION_USER");
        model.addAttribute("admin", admin);
        return "admin/editMyInfo";
    }

    @RequestMapping("/editApp")
    @ResponseBody
    public Map<String, String> editApp(@RequestParam int appId, @RequestParam String appType,HttpSession session) {
        Map<String, String> res = new HashMap<>();
        Admin optAdmin = (Admin)session.getAttribute("SESSION_USER");
        App app = appService.getAppById(appId);
        appService.editAppType(appId, appType);
        appService.recordEdit(appId,"app_type",app.getAppType(),appType,optAdmin.getAdminId(),new Date());
        res.put("status", "1");
        res.put("message", "修改成功");
        return res;
    }

    @RequestMapping("/editAdmin")
    @ResponseBody
    public Map<String, String> editAdmin(HttpServletRequest request,HttpSession session) {
        Map<String, String> res = new HashMap<>();
        int editType = Integer.parseInt(request.getParameter("editType"));
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        Admin optAdmin = (Admin)session.getAttribute("SESSION_USER");
        Admin admin = adminService.getAdminById(adminId);
        if (editType == 0) {//修改电话
            long adminTel = Long.parseLong(request.getParameter("adminTel"));
            if (adminService.getAdminByTel(adminTel) != null) {
                res.put("status", "2");
                res.put("message", "该手机号已存在");
                return res;
            } else {
                adminService.editAdminTel(adminId, adminTel);
                adminService.recordEdit(adminId, "admin_tel", String.valueOf(admin.getAdminTel()), String.valueOf(adminTel),optAdmin.getAdminId(),new Date());
                res.put("status", "1");
                res.put("message", "修改成功");
                return res;
            }
        } else if (editType == 1) {//修改密码
            String oldPwd = request.getParameter("oldPwd");
            oldPwd = DigestUtils.md5Hex(oldPwd);
            if (oldPwd.equals(adminService.getAdminById(adminId).getAdminPwd())) {
                String adminPwd = request.getParameter("adminPwd");
                adminPwd = DigestUtils.md5Hex(adminPwd);
                adminService.editAdminPwd(adminId, adminPwd);
                adminService.recordEdit(adminId, "admin_pwd", admin.getAdminPwd(), adminPwd,optAdmin.getAdminId(),new Date());
                res.put("status", "1");
                res.put("message", "修改成功");
                return res;
            } else {
                res.put("status", "2");
                res.put("message", "原密码错误");
                return res;
            }
        }
        res.put("status", "2");
        res.put("message", "出现错误，请稍后再试");
        return res;
    }

    //查
    @RequestMapping("/myInfo")
    public String myInfo(Model model, HttpSession session) {
        int adminId = ((Admin) session.getAttribute("SESSION_USER")).getAdminId();
        Admin admin = adminService.getAdminById(adminId);
        model.addAttribute("admin", admin);
        return "admin/myInfo";
    }

    @RequestMapping("/searchApp")
    public String searchApp(@RequestParam String keyword, Model model) {
        List<App> appList = appService.getAppByNameOrType(keyword);
        model.addAttribute("appList", appList);
        return "admin/app";
    }

    @RequestMapping("/searchUser")
    public String searchUser(@RequestParam String keyword, Model model) {
        List<User> userList = userService.getUserByIdOrName(keyword);
        Map<Integer, Integer> parentNum = new HashMap<Integer, Integer>();
        for (User user : userList) {
            parentNum.put(user.getUserId(), relationService.getParentNum(user.getUserId()));
        }
        model.addAttribute("userList", userList);
        model.addAttribute("parentNum", parentNum);
        return "admin/user";
    }

    @RequestMapping("/searchParent")
    public String searchParent(@RequestParam String keyword, Model model) {
        List<Parent> parentList = parentService.getParentByIdOrName(keyword);
        Map<Integer, Integer> childNum = new HashMap<Integer, Integer>();
        for (Parent parent : parentList) {
            childNum.put(parent.getParentId(), relationService.getChildNum(parent.getParentId()));
        }
        model.addAttribute("parentList", parentList);
        model.addAttribute("childNum", childNum);
        return "admin/parent";
    }

    @RequestMapping("/searchAdmin")
    public String searchAdmin(@RequestParam String keyword, Model model) {
        List<Admin> adminList = adminService.getAdminByIdOrName(keyword);
        model.addAttribute("adminList", adminList);
        return "admin/admin";
    }

}
