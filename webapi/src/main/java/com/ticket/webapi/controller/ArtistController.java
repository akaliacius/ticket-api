package com.ticket.webapi.controller;

import com.ticket.artist.model.Artist;
import com.ticket.artist.service.ArtistService;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import org.reactivestreams.Publisher;

@Controller("/api/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher<Artist> listAll(){
        return artistService.getAllArtists();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Publisher<Artist> getArtist(@PathVariable String id){
        return artistService.getArtistById(id);
    }
}
