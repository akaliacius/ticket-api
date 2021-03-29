package com.ticket.venue.service;

import com.ticket.common.util.UncheckedJsonMapper;
import com.ticket.venue.model.Venue;
import io.micronaut.context.annotation.Prototype;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.Optional;

import static com.ticket.common.http.Requests.asyncRequest;
import static com.ticket.common.http.Requests.makeRequest;

@Prototype
public final class VenueS3ApiService implements VenueService {
    public static final String SOURCE = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/venues.json";
    private static Logger logger = LoggerFactory.getLogger(VenueS3ApiService.class);

    @Override
    public Flowable<Venue> getAllVenues() {
        return Flowable.fromCallable(() -> makeRequest(SOURCE))
                .subscribeOn(Schedulers.io())
                .map(request -> asyncRequest(request, HttpResponse.BodyHandlers.ofString()))
                .flatMap(future -> Flowable.fromCompletionStage(future))
                .map(HttpResponse::body)
                .map(body -> new UncheckedJsonMapper<Venue>().mapToList(body))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Flowable::fromIterable);
    }

    @Override
    public Flowable<Venue> getVenueById(String id) {
        return getAllVenues()
                .filter(venue -> venue.id().equals(id))
                .take(1);
    }
}
