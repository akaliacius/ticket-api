package com.ticket.venue.service;

import com.ticket.venue.model.Venue;
import io.reactivex.rxjava3.core.Flowable;

public sealed interface VenueService permits VenueS3ApiService {
    Flowable<Venue> getAllVenues();
    Flowable<Venue> getVenueById(String id);
}
