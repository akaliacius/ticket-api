package com.ticket.event.service;

import com.ticket.common.util.UncheckedJsonMapper;
import com.ticket.event.model.Event;
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
public final class EventS3ApiService implements EventService {
    public static final String SOURCE = "https://iccp-interview-data.s3-eu-west-1.amazonaws.com/78656681/events.json";
    private static Logger logger = LoggerFactory.getLogger(EventS3ApiService.class);

    @Override
    public Flowable<Event> getAllEvents() {
        return Flowable.fromCallable(() -> makeRequest(SOURCE))
                .subscribeOn(Schedulers.io())
                .map(request -> asyncRequest(request, HttpResponse.BodyHandlers.ofString()))
                .flatMap(future -> Flowable.fromCompletionStage(future))
                .map(HttpResponse::body)
                .map(body -> new UncheckedJsonMapper<Event>().mapToList(body))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .flatMap(Flowable::fromIterable);
    }

    @Override
    public Flowable<Event> getEventById(String id){
        return getAllEvents()
                .filter(event -> event.id().equals(id))
                .take(1);
    }
}
