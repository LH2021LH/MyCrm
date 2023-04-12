package com.lh.crm.settings.web.controller;

import com.lh.crm.common.codedomain.UserCode;
import com.lh.crm.common.constant.CodeType;
import com.lh.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/settings/qx/user")
public class UserController {
    private String path = "/settings/qx/user/";
    @GetMapping("/tologin")
    public String tologin(){
        return path +
                "login";
    }

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public @ResponseBody Object login(String loginAct,
                                      String loginPwd,
                                      Boolean isRemember,
                                      HttpSession httpSession,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        map.put("allowIps",request.getRemoteAddr());
        Map<String, Object> code = userService.jutifyUserByPwdAndUsn(map);
        UserCode userCode = new UserCode();
        userCode.setCode(code.get("code").toString());
        userCode.setMsg(CodeType.fromTypeName(code.get("code").toString()).msg());
       /* if ("4".equals(code)){
            //未可知先空着,看看后面她有没有处理
            httpSession.setAttribute("userSession", u);
        }*/
        if ("4".equals(code.get("code").toString())){
            httpSession.setAttribute("userName", code.get("userName").toString());

            if(isRemember){
                Cookie c1 = new Cookie("loginAct",loginAct);
                c1.setMaxAge(10*24*60*60);
                response.addCookie(c1);
                Cookie c2 = new Cookie("loginPwd",loginPwd);
                c2.setMaxAge(10*24*60*60);
                response.addCookie(c2);
            }else {
                Cookie c1 = new Cookie("loginAct","1");
                c1.setMaxAge(0);
                response.addCookie(c1);
                Cookie c2 = new Cookie("loginPwd","1");
                c2.setMaxAge(0);
                response.addCookie(c2);
            }
        }
        System.out.println("==============================================================");
        System.out.println( request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/");
        return userCode;
    }

    @GetMapping("/loginOut")
    public  String loginOut(HttpServletResponse httpServletResponse, HttpSession httpSession) {
        Cookie c1 = new Cookie("loginAct", "1");
        c1.setMaxAge(0);
        httpServletResponse.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd", "1");
        c2.setMaxAge(0);
        httpServletResponse.addCookie(c2);
        httpSession.invalidate();
        return "redirect:/";
    }
}
