package com.ticket.artist.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.artist.model.Artist;
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

public class ArtistService {
    public static final String SOURCE = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/artists.json";
    private static Logger logger = LoggerFactory.getLogger(ArtistService.class);


    public Observable<Artist> getAllArtists() {
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

    public Maybe<Artist> getArtistById(String id){
        return getAllArtists()
                .filter(artist -> artist.id().equals(id))
                .firstElement();
    }

    private Optional<List<Artist>> mapToList(String body){
        try {
            return Optional.of(new ObjectMapper().readValue(body, new TypeReference<>(){}));
        } catch (JsonProcessingException e) {
            logger.error("failed to read JSON", e);
            return Optional.empty();
        }
    }
}
