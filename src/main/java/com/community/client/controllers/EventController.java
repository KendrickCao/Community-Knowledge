package com.community.client.controllers;

import com.community.client.models.Community;
import com.community.client.models.Event;
import com.community.client.models.Project;
import com.community.client.services.CommunityService;
import com.community.client.services.EventService;
import com.community.client.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class EventController {

    //DI the event service
    private final EventService eventService;
    private final CommunityService communityService;
    private final ProjectService projectService;

    public EventController(EventService eventService, CommunityService communityService, ProjectService projectService) {
        this.eventService = eventService;
        this.communityService = communityService;
        this.projectService = projectService;
    }

    //Controller to create an event for the community
    @PostMapping("/api/add-event/community/{communityId}")
    public Event addEventToCommunity(@RequestBody Event event,@PathVariable Long communityId){
        //Get the community object based on the ID
        Community community = communityService.getCommunityById(communityId);

        //Create a new instance of Event by saving the Request Body
        Event savedEvent = eventService.saveEvent(event);
        //Updating the saved event with the community fetched based on ID
        savedEvent.setCommunity(community);
        //Get a Set of existing events of the community
        Set<Event> eventsInCommunity = community.getEvent();
        //Add newly saved event to that set
        eventsInCommunity.add(savedEvent);
        //Using setters update the community with the updated set of events
        community.setEvent(eventsInCommunity);
        //Use community service save method to persist the community in DB
        communityService.saveCommunity(community);
        return savedEvent;
    }

    //Contoller to create an event for the project
    @PostMapping("/api/add-event/project/{projectId}")
    public Event addEventToProject(@RequestBody Event event,@PathVariable Long projectId){
        Project project = projectService.getProjectById(projectId);
        Event savedEvent = eventService.saveEvent(event);
        savedEvent.setProject(project);
        Set<Event> eventsInProject = project.getEvent();
        eventsInProject.add(savedEvent);
        project.setEvent(eventsInProject);
        projectService.saveProject(project);
        return savedEvent;
    }

    @GetMapping("/api/events")
    private Set<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/api/get-event/{id}")
    private Event getEventById(@PathVariable Long id){
        return eventService.getEventById(id);
    }

    @GetMapping("/api/event/parent/{parentId}")
    private Set<Event> getEventForCommunityOrProject(
            @RequestParam("parent") String parent,
            @PathVariable Long parentId){
        Set<Event> eventsFromParent = null;
        if (parent.equals("community")) {
            Community community = communityService.getCommunityById(parentId);
            eventsFromParent = community.getEvent();
            return eventsFromParent;
        } else if (parent.equals("project")) {
            Project project = projectService.getProjectById(parentId);
            eventsFromParent = project.getEvent();
            return eventsFromParent;
        }
        return null;
    }
}
