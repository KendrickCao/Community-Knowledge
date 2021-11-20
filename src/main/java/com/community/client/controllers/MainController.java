package com.community.client.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}
