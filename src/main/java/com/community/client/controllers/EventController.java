package com.community.client.controllers;

import com.community.client.models.Event;
import com.community.client.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/api/add-event/userId/{userId}")
    public Event addEvent(@RequestBody Event event, @PathVariable Long userId){
        Event savedEvent = eventService.saveEvent(event);
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
}
