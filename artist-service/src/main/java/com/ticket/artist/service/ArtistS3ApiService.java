package com.ticket.artist.service;

import com.ticket.artist.model.Artist;
import com.ticket.common.util.UncheckedJsonMapper;
import io.micronaut.context.annotation.Prototype;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.net.http.HttpResponse;
import java.util.Optional;

import static com.ticket.common.http.Requests.asyncRequest;
import static com.ticket.common.http.Requests.makeRequest;

@Prototype
public final class ArtistS3ApiService implements ArtistService {
    public static final String SOURCE = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/artists.json";
    private static Logger logger = LoggerFactory.getLogger(ArtistS3ApiService.class);

    @PostConstruct
    public void log() {
        logger.info("ArtistService: {}", this);
    }

    @Override
    public Flowable<Artist> getAllArtists() {
        return Flowable.fromCallable(() -> makeRequest(SOURCE))
                .subscribeOn(Schedulers.io())
                .map(request -> asyncRequest(request, HttpResponse.BodyHandlers.ofString()))
                .flatMap(future -> Flowable.fromCompletionStage(future))
                .map(HttpResponse::body)
                .map(body -> new UncheckedJsonMapper<Artist>().mapToList(body))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Flowable::fromIterable);
    }

    @Override
    public Flowable<Artist> getArtistById(String id) {
        return getAllArtists()
                .filter(artist -> artist.id().equals(id))
                .take(1);
    }
}