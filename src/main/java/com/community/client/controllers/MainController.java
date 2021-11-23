package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.services.CommunityService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RestController
public class MainController {

    //DI the community service
    private CommunityService communityService;

    public MainController(CommunityService communityService) {
        this.communityService = communityService;
    }

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

    @GetMapping("/CreateProject")
    public ModelAndView showCreateProjectPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/project/CreateProject");
        return modelAndView;
    }

    @RequestMapping("/add-community")
    public ModelAndView addCommunity(ModelAndView modelAndView){
        modelAndView.setViewName("create-community/index");
        return modelAndView;
    }

    @RequestMapping("/communities")
    public ModelAndView viewCommunities(ModelAndView modelAndView){
        Set<Community> communities = communityService.getAllCommunities();
        modelAndView.setViewName("community-listview/index");
        modelAndView.addObject("communities", communities);
        return modelAndView;
    }
}
