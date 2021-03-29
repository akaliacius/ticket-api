package com.ticket.webapi;

import io.micronaut.runtime.Micronaut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebApi {
    private static Logger logger = LoggerFactory.getLogger(WebApi.class);

    public static void main(String[] args) throws InterruptedException {
        Micronaut.run(WebApi.class);
    }
}
