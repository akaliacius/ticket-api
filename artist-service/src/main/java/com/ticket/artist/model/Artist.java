package com.ticket.artist.model;

public record Artist(
        String name,
        String id,
        String imgSrc,
        String url,
        int rank) {}
