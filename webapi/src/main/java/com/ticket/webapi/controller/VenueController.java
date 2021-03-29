package com.ticket.webapi.controller;

import com.ticket.venue.model.Venue;
import com.ticket.venue.service.VenueService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import org.reactivestreams.Publisher;

@Controller("/api/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher<Venue> listAll(){
        return venueService.getAllVenues();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher<Venue> getVenue(@PathVariable String id){
        return venueService.getVenueById(id);
    }
}
