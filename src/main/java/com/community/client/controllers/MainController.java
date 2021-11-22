package com.community.client.controllers;

import com.community.client.models.Community;
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

    //Controller to load the VIEW which allows user to create a new community
    @RequestMapping("/add-community")
    public ModelAndView addCommunity(ModelAndView modelAndView){
        modelAndView.setViewName("create-community/index");
        return modelAndView;
    }

    //Controller which allows the user the VIEW the details of a particular community
    @RequestMapping("/community/{communityId}")
    public ModelAndView viewCommunityDetails(ModelAndView modelAndView, @PathVariable Long communityId){
        //Get the community by ID
        ModelAndView modelAndView1 = new ModelAndView("community-detail.index");
        return modelAndView;
    }
}
