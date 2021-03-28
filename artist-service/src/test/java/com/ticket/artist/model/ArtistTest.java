package com.ticket.artist.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArtistTest {
    private static final String ARTIST_JSON = """
            {
                  "name":"HRH Prog",
                  "id":"21",
                  "imgSrc":"//some-base-url/hrh-prog.jpg",
                  "url":"/hrh-prog-tickets/artist/21",
                  "rank":1
            }
    """;

    @Test
    void venue_can_be_deserialized() throws Exception {
        var artist = new ObjectMapper().readValue(ARTIST_JSON, Artist.class);
        assertNotNull(artist);
        assertEquals("HRH Prog", artist.name());
        assertEquals("21", artist.id());
        assertEquals("//some-base-url/hrh-prog.jpg", artist.imgSrc());
        assertEquals("/hrh-prog-tickets/artist/21", artist.url());
        assertEquals(1, artist.rank());
    }
}
