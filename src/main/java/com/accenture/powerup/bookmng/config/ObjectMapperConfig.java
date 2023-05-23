package com.accenture.powerup.bookmng.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@Component
public class ObjectMapperConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void registerJavaTimeModule() {
        objectMapper.registerModule(new JavaTimeModule()).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .disable(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.mappedFeature()).getSerializerProvider()
            .setNullValueSerializer(NullSerializer.instance);
    }
}
