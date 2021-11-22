package com.community.client.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @GetMapping("/SignUp")
    public ModelAndView showSignUpPage(ModelAndView modelAndView){
        modelAndView = new ModelAndView("/signup/SignUp");
        return modelAndView;
    }

    @GetMapping("/Login")
    public ModelAndView showLoginPage(ModelAndView modelAndView){
        modelAndView = new ModelAndView("/login/Login");
        return modelAndView;
    }

    @RequestMapping("/add-community")
    public ModelAndView addCommunity(ModelAndView modelAndView){
        modelAndView.setViewName("create-community/index");
        return modelAndView;
    }
}
