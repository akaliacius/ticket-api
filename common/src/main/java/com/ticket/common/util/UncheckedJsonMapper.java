package com.ticket.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class UncheckedJsonMapper<X> {
    private static Logger logger = LoggerFactory.getLogger(UncheckedJsonMapper.class);

    public Optional<List<X>> mapToList(String body){
        try {
            return Optional.of(new ObjectMapper().readValue(body, new TypeReference<>(){}));
        } catch (JsonProcessingException e) {
            logger.error("failed to read JSON", e);
            return Optional.empty();
        }
    }
}
