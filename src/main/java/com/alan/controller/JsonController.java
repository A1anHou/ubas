package com.alan.controller;

import com.alan.model.Admin;
import com.alan.model.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/json")
public class JsonController {
    @RequestMapping("/m1")
    @ResponseBody
    public Admin m1(){
        System.out.println("123");
        Admin admin = new Admin();
        admin.setAdminName("小狗子");
        admin.setAdminPwd("123");
        return admin;
    }

    //前台提交一个Admin对象
    @RequestMapping("/m2")
    public String m2(@RequestBody Admin user){

        System.err.println("123");
        System.out.println(user.toString());

        return "admin/index";
    }
}
