package com.example.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
}
