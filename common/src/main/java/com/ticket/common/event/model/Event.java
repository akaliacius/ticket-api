package com.ticket.common.event.model;

import java.util.List;
import java.util.Map;

public record Event (
        String title,
        String id,
        String dateStatus,
        String timeZone,
        String startDate,
        List<Map<String, String>> artists,
        Map<String, String> venue,
        boolean hiddenFromSearch) {}
