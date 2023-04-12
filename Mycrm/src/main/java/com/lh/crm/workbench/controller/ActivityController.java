package com.lh.crm.workbench.controller;

import com.lh.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {
    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String activityIndex(Model model){
        List<String> userNameList = userService.queryAllUserNameList();
        model.addAttribute("userNameList", userNameList);
        return "workbench/activity/index";
    }


}
