package com.ticket.venue.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.venue.model.Venue;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import static com.ticket.common.http.Requests.asyncRequest;
import static com.ticket.common.http.Requests.makeRequest;

public class VenueService {
    public static final String SOURCE = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/venues.json";
    private static Logger logger = LoggerFactory.getLogger(VenueService.class);


    public Observable<Venue> getAllVenues() {
        return Observable.fromCallable(() -> makeRequest(SOURCE))
                .subscribeOn(Schedulers.io())
                .map(request -> asyncRequest(request, HttpResponse.BodyHandlers.ofString()))
                .flatMap(future -> Observable.fromCompletionStage(future))
                .map(HttpResponse::body)
                .map(this::mapToList)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Observable::fromIterable);
    }

    public Maybe<Venue> getVenueById(String id){
        return getAllVenues()
                .filter(artist -> artist.id().equals(id))
                .firstElement();
    }

    private Optional<List<Venue>> mapToList(String body){
        try {
            return Optional.of(new ObjectMapper().readValue(body, new TypeReference<>(){}));
        } catch (JsonProcessingException e) {
            logger.error("failed to read JSON", e);
            return Optional.empty();
        }
    }
}
