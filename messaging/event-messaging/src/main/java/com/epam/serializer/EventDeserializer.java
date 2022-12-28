package com.epam.serializer;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.context.annotation.Profile;

import com.epam.entity.Event;
import com.fasterxml.jackson.databind.ObjectMapper;

@Profile("kafka")
public class EventDeserializer implements Deserializer<Event> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) { }

    @Override
    @SneakyThrows
    public Event deserialize(String topic, byte[] data) {
        if (Objects.isNull(data)) {
            return null;
        }
        return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), Event.class);
    }

    @Override
    public void close() { }

}
