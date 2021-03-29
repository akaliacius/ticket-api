package com.ticket.venue.service;

import com.ticket.venue.model.Venue;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

public sealed interface VenueService permits VenueS3ApiService {
    Observable<Venue> getAllVenues();
    Maybe<Venue> getVenueById(String id);
}
