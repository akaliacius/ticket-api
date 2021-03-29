package com.ticket.artist.service;

import com.ticket.artist.model.Artist;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

public sealed interface ArtistService permits ArtistS3ApiService {
    Observable<Artist> getAllArtists();
    Maybe<Artist> getArtistById(String id);
}
