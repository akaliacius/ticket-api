package com.ticket.venue.service;

import com.ticket.common.util.UncheckedJsonMapper;
import com.ticket.venue.model.Venue;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.Optional;

import static com.ticket.common.http.Requests.asyncRequest;
import static com.ticket.common.http.Requests.makeRequest;

public final class VenueS3ApiService implements VenueService {
    public static final String SOURCE = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/venues.json";
    private static Logger logger = LoggerFactory.getLogger(VenueS3ApiService.class);

    @Override
    public Observable<Venue> getAllVenues() {
        return Observable.fromCallable(() -> makeRequest(SOURCE))
                .subscribeOn(Schedulers.io())
                .map(request -> asyncRequest(request, HttpResponse.BodyHandlers.ofString()))
                .flatMap(future -> Observable.fromCompletionStage(future))
                .map(HttpResponse::body)
                .map(body -> new UncheckedJsonMapper<Venue>().mapToList(body))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Observable::fromIterable);
    }

    @Override
    public Maybe<Venue> getVenueById(String id) {
        return getAllVenues()
                .filter(venue -> venue.id().equals(id))
                .firstElement();
    }
}
