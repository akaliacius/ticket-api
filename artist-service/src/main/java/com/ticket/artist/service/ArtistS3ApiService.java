package com.ticket.artist.service;

import com.ticket.artist.model.Artist;
import com.ticket.common.util.UncheckedJsonMapper;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;
import java.util.Optional;

import static com.ticket.common.http.Requests.asyncRequest;
import static com.ticket.common.http.Requests.makeRequest;

public final class ArtistS3ApiService implements ArtistService {
    public static final String SOURCE = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/artists.json";
    private static Logger logger = LoggerFactory.getLogger(ArtistS3ApiService.class);

    @Override
    public Observable<Artist> getAllArtists() {
        return Observable.fromCallable(() -> makeRequest(SOURCE))
                .subscribeOn(Schedulers.io())
                .map(request -> asyncRequest(request, HttpResponse.BodyHandlers.ofString()))
                .flatMap(future -> Observable.fromCompletionStage(future))
                .map(HttpResponse::body)
                .map(body -> new UncheckedJsonMapper<Artist>().mapToList(body))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Observable::fromIterable);
    }

    @Override
    public Maybe<Artist> getArtistById(String id){
        return getAllArtists()
                .filter(artist -> artist.id().equals(id))
                .firstElement();
    }
}