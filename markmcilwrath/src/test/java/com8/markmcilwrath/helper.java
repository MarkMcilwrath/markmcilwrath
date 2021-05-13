package com8.markmcilwrath;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class helper {

    private static  final ObjectMapper MAPPER = new ObjectMapper();

    public static String toJson(Object value) throws JsonProcessingException {
        return MAPPER.writeValueAsString(value);
    }
}
