package com.lh.crm.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workbench")
public class LoginController {
    @RequestMapping("/index")
    public String index(){
        return "workbench/index";
    }
}
