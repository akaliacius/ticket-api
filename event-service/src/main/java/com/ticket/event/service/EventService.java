package com.ticket.event.service;

import com.ticket.event.model.Event;
import io.reactivex.rxjava3.core.Flowable;

public sealed interface EventService permits EventS3ApiService {
    Flowable<Event> getAllEvents();
    Flowable<Event> getEventById(String id);
}
