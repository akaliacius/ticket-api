package com.ticket.common.venue.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class VenueTest {

    private static final String VENUE_JSON = """
        {
              "name":"O2 Academy Sheffield",
              "url":"/o2-academy-sheffield-tickets-sheffield/venue/41",
              "city":"Sheffield",
              "id":"41"
        }
    """;

    @Test
    void venue_can_be_deserialized() throws Exception {
        var venue = new ObjectMapper().readValue(VENUE_JSON, Venue.class);
        assertNotNull(venue);
        assertEquals("O2 Academy Sheffield", venue.name());
        assertEquals("/o2-academy-sheffield-tickets-sheffield/venue/41", venue.url());
        assertEquals("Sheffield", venue.city());
        assertEquals("41", venue.id());
    }
}
