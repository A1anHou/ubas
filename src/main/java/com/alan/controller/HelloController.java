package com.alan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/girl")
    public String girl(Model model){
        model.addAttribute("girl","菲菲");
        return "girl";
    }
}
