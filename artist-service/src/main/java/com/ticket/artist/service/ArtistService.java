package com.ticket.artist.service;

import com.ticket.artist.model.Artist;
import io.reactivex.rxjava3.core.Flowable;

public sealed interface ArtistService permits ArtistS3ApiService {
    Flowable<Artist> getAllArtists();
    Flowable<Artist> getArtistById(String id);
}
