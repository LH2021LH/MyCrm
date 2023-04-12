package com.lh.crm.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workbench/main")
public class MainController {

    @GetMapping("/index")
    public String mainIndex() {
        return "workbench/main/index";
    }
}
