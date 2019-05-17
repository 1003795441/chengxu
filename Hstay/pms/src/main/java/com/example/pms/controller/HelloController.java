package com.example.pms.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
    @RequestMapping(value = "/login/form")
    public String hello(){
        return "hello";
    }


    @RequestMapping(value = "/login/form1")
    public String hello1(){
        return "hello1";
    }

    /**
     * 登录页面(记住我功能有效情况下，在下次进入登陆页面直接跳到个人首页)
     * @param
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(Model model
            , @RequestParam(value = "error",required = false) boolean error
            , @RequestParam(value = "logout",required = false) boolean logout, HttpServletRequest request){
        //如果已经登陆跳转到个人首页
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&&
                !authentication.getPrincipal().equals("anonymousUser")&&
                authentication.isAuthenticated())
            return "me";
        if(error==true)
            model.addAttribute("error",error);
        if(logout==true)
            model.addAttribute("logout",logout);
        return "login";
    }
}
