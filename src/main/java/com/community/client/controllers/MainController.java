package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.models.Event;
import com.community.client.models.Project;
import com.community.client.models.UserObject;
import com.community.client.services.CommunityService;
import com.community.client.services.EventService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RestController
public class MainController {

    //DI the community service
    private CommunityService communityService;

    //DI the event service
    private EventService eventService;

    public MainController(CommunityService communityService, EventService eventService) {
        this.communityService = communityService;
        this.eventService = eventService;
    }

    @GetMapping("/SignUp")
    public ModelAndView showSignUpPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/signup/SignUp");
        return modelAndView;
    }
    @GetMapping("/")
    public ModelAndView showLandingPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/landing/landing");
        return modelAndView;
    }

    @GetMapping("/Login")
    public ModelAndView showLoginPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/login/Login");
        return modelAndView;
    }

    @GetMapping("/CreateProject")
    public ModelAndView showCreateProjectPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/project/CreateProject");

    @GetMapping("/EventForm")
    public ModelAndView showEventPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/event/EventForm");
        return modelAndView;
    }

    //Controller to load the VIEW which allows user to create a new community
    @RequestMapping("/add-community")
    public ModelAndView addCommunity(ModelAndView modelAndView) {
        modelAndView.setViewName("create-community/index");
        return modelAndView;
    }


    //Controller which allows the user the VIEW the details of a particular community
    @RequestMapping("/community/{communityId}")
    public ModelAndView viewCommunityDetails(ModelAndView modelAndView, @PathVariable Long communityId) {
        //Get the community by ID
        Community community = communityService.getCommunityById(communityId);
        Set<Project> projectSet = community.getProjectSet();
        Set<UserObject> userObjectSet = community.getUserObjectSet();

        String totalUsers = String.valueOf(userObjectSet.size());
        String totalProjects = String.valueOf(projectSet.size());
        System.out.println(totalUsers);
        System.out.println(totalProjects);
        modelAndView.addObject("community", community);
        modelAndView.addObject("communityProjects", projectSet);
        modelAndView.addObject("communityUsers", userObjectSet);
        modelAndView.addObject("communityUsersCount", totalUsers );
        modelAndView.addObject("communityProjectsCount", totalProjects);

        modelAndView.setViewName("community-detail/index");
        return modelAndView;
    }

    //Controller which allows the user the VIEW to a list of all communities
    @RequestMapping("/communities")
    public ModelAndView viewCommunities(ModelAndView modelAndView) {
        Set<Community> communities = communityService.getAllCommunities();
        modelAndView.setViewName("community-listview/index");
        modelAndView.addObject("communities", communities);
        return modelAndView;
    }

    //Controller which allows the user the VIEW the details of a particular event
    @RequestMapping("/event/{eventId}")
    public ModelAndView viewEventDetails(ModelAndView modelAndView, @PathVariable Long eventId) {
        //Get the event by ID
        Event event = eventService.getEventById(eventId);
        Community eventCommunity = event.getCommunity();
        Project eventProject = event.getProject();
        modelAndView.addObject("event", event);
            modelAndView.addObject("eventCommunity",eventCommunity );
            modelAndView.addObject("eventProject", eventProject);


        modelAndView.setViewName("event-detail/EventDetail");
        return modelAndView;
    }
}
