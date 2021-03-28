package com.ticket.artist.util;

public enum Endpoint {
    ARTIST_ALL("internal/artists"),
    ARTIST_ID("internal/artists/:id");

    public final String path;
    Endpoint(String path) {
        this.path = path;
    }
}
