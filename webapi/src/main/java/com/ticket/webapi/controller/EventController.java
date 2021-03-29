package com.ticket.webapi.controller;

import com.ticket.event.model.Event;
import com.ticket.event.service.EventService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import org.reactivestreams.Publisher;

@Controller("/api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher<Event> listAll(){
        return eventService.getAllEvents();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher<Event> getEvent(@PathVariable String id) {
        return eventService.getEventById(id);
    }
}
