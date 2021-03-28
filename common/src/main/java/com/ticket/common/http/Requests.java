package com.ticket.common.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static java.time.temporal.ChronoUnit.SECONDS;

public final class Requests {
    private Requests() {
        throw new RuntimeException("no instance of this class");
    }

    public static HttpRequest makeRequest(String uri) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(new URI(uri))
                .timeout(Duration.of(60, SECONDS))
                .header("Accept", "application/json")
                .GET()
                .build();
    }

    public static CompletableFuture<HttpResponse<String>> asyncRequest(HttpRequest request, HttpResponse.BodyHandler handler){
        return HttpClient.newBuilder()
                .build()
                .sendAsync(request, handler);
    }
}
