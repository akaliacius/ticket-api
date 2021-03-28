package com.ticket.artist;

import static com.ticket.artist.util.Endpoint.ARTIST_ALL;
import static com.ticket.artist.util.Endpoint.ARTIST_ID;
import static spark.Spark.*;

public class ArtistService {
    public static void main(String[] args) {
        var port = 7777;
        port(port);
        get(ARTIST_ALL.path, (req, res) -> "lists all artists");
        get(ARTIST_ID.path, (req, res) -> "gets artist with id " + req.params(":id"));
    }
}
