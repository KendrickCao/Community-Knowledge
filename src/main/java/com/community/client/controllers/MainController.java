package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.models.Event;
import com.community.client.models.Project;
import com.community.client.models.UserObject;
import com.community.client.services.CommunityService;
import com.community.client.services.ProjectService;
import com.community.client.services.EventService;
import com.community.client.services.UserObjectService;

import org.hibernate.annotations.SourceType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

@RestController
public class MainController {

    // DI the community service
    private CommunityService communityService;

    // DI the event service
    private EventService eventService;

    // DI the user service
    private UserObjectService userObjectService;

    // DI project service
    private ProjectService projectService;

    public MainController(CommunityService communityService, EventService eventService,
            UserObjectService userObjectService, ProjectService projectService) {
        this.communityService = communityService;
        this.eventService = eventService;
        this.userObjectService = userObjectService;
        this.projectService = projectService;
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

    @RequestMapping("/transaction")
    public ModelAndView showTransactionPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/transaction/transaction");
        return modelAndView;
    }

    @GetMapping("/Login")
    public ModelAndView showLoginPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/login/Login");
        return modelAndView;
    }

    @GetMapping("/UserProfile")
    public ModelAndView showUserProfilePage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("/userprofile/UserProfile");
        return modelAndView;
    }

    @GetMapping("/project/create")
    public ModelAndView showCreateProjectPage(ModelAndView modelAndView) {
        modelAndView = new ModelAndView("project/createProject");
        return modelAndView;
    }

    @GetMapping("/EventForm")
    public ModelAndView showEventPage(ModelAndView modelAndView) {

        modelAndView = new ModelAndView("/event/EventForm");
        return modelAndView;
    }

    // Controller to load the VIEW which allows user to create a new community
    @RequestMapping("/add-community")
    public ModelAndView addCommunity(ModelAndView modelAndView) {
        modelAndView.setViewName("create-community/index");
        return modelAndView;
    }

    // Controller which allows the user the VIEW the details of a particular
    // community
    @RequestMapping("/community/{communityId}")
    public ModelAndView viewCommunityDetails(ModelAndView modelAndView, @PathVariable Long communityId) {
        // Get the community by ID
        Community community = communityService.getCommunityById(communityId);
        Set<Project> projectSet = community.getProjectSet();
        Set<UserObject> userObjectSet = community.getUserObjectSet();
        Set<Event> eventSet = community.getEvent();

        String totalUsers = String.valueOf(userObjectSet.size());
        String totalProjects = String.valueOf(projectSet.size());
        String totalEvents = String.valueOf(eventSet.size());
        modelAndView.addObject("community", community);
        modelAndView.addObject("communityProjects", projectSet);
        modelAndView.addObject("communityEvents", eventSet);
        modelAndView.addObject("communityUsers", userObjectSet);
        modelAndView.addObject("communityUsersCount", totalUsers);
        modelAndView.addObject("communityProjectsCount", totalProjects);
        modelAndView.addObject("communityEventsCount", totalEvents);

        modelAndView.setViewName("community-detail/index");
        return modelAndView;
    }

    // Controller which allows the user the VIEW to a list of all communities
    @RequestMapping("/communities")
    public ModelAndView viewCommunities(ModelAndView modelAndView,
            @RequestParam(name = "keyword", required = false) String keyword) {
        Set<Community> communities = new HashSet<>();
        if (keyword != null) {
            communities = communityService.getCommunitySearchResults(keyword);
        } else {
            communities = communityService.getAllCommunities();
        }
        System.out.println(communities.size());
        modelAndView.setViewName("community-listview/index");
        modelAndView.addObject("communities", communities);
        return modelAndView;
    }

    // Controller to view the events of a community/project
    @RequestMapping("/event/parent/{parentId}")
    public ModelAndView viewEventForCommunityOrProject(ModelAndView modelAndView,
            @RequestParam("parent") String parent,
            @PathVariable Long parentId) {
        if (parent.equals("community")) {
            Community community = communityService.getCommunityById(parentId);
            Set<Event> eventsFromParent = community.getEvent();
            modelAndView.addObject("eventsFromParent", eventsFromParent);
        } else if (parent.equals("project")) {
            Project project = projectService.getProjectById(parentId);
            Set<Event> eventsFromParent = project.getEvent();
            modelAndView.addObject("eventsFromParent", eventsFromParent);
        }
        modelAndView.setViewName("event-listview/EventList");
        return modelAndView;
    }

    // Controller which allows the user the VIEW the details of a particular event
    @RequestMapping("/event/{eventId}")
    public ModelAndView viewEventDetails(ModelAndView modelAndView, @PathVariable Long eventId) {
        // Get the event by ID
        Event event = eventService.getEventById(eventId);
        Community eventCommunity = event.getCommunity();
        Project eventProject = event.getProject();
        modelAndView.addObject("event", event);
        modelAndView.addObject("eventCommunity", eventCommunity);
        modelAndView.addObject("eventProject", eventProject);
        modelAndView.setViewName("event-detail/EventDetail");
        return modelAndView;
    }

    // Controller which allows the user the VIEW to a list of all projects
    @RequestMapping("/projects")
    public ModelAndView viewProjects(ModelAndView modelAndView) {
        Set<Project> projects = projectService.getAllProjects();
        modelAndView.setViewName("project/projects");
        modelAndView.addObject("projects", projects);
        return modelAndView;
    }

    @RequestMapping("/project/{projectId}")
    public ModelAndView viewProjects(ModelAndView modelAndView, @PathVariable Long projectId) {
        Project project = projectService.getProjectById(projectId);
        Community projectCommunity = project.getCommunity();
        Set<Event> projectEvents = project.getEvent();
        modelAndView.setViewName("project/projectDetail");
        modelAndView.addObject("project", project);
        modelAndView.addObject("projectCommunity", projectCommunity);
        modelAndView.addObject("projectEvents", projectEvents);
        return modelAndView;
    }
}
