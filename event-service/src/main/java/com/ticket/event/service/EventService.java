package com.ticket.event.service;

import com.ticket.event.model.Event;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

public sealed interface EventService permits EventS3ApiService {
    Observable<Event> getAllEvents();
    Maybe<Event> getEventById(String id);
}
