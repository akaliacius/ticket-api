package com.ticket.event.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private static final String EVENT_JSON = """
            {
                  "title":"Fusion Prog",
                  "id":"1",
                  "dateStatus":"singleDate",
                  "timeZone":"Europe/London",
                  "startDate":"2020-10-17T00:00:00",
                  "artists":[
                     {
                        "id":"21"
                     },
                     {
                        "id":"23"
                     },
                     {
                        "id":"26"
                     }
                  ],
                  "venue":{
                     "id":"41"
                  },
                  "hiddenFromSearch":false
            }
    """;

    @Test
    void event_can_be_deserialized() throws Exception {
        var event = new ObjectMapper().readValue(EVENT_JSON, Event.class);
        assertNotNull(event);
        assertEquals("Fusion Prog", event.title());
        assertEquals("1", event.id());
        assertEquals("singleDate", event.dateStatus());
        assertEquals("Europe/London", event.timeZone());
        assertEquals("2020-10-17T00:00:00", event.startDate());
        assertEquals(3L, event.artists().stream()
                .map(m -> m.get("id"))
                .filter(idCheck("21").or(idCheck("23").or(idCheck("26"))))
                .count()
        );
        assertEquals("41", event.venue().get("id"));
        assertFalse(event.hiddenFromSearch());
    }

    private Predicate<String> idCheck(String id){
        return i -> i.equals(id);
    }
}
