package com.epam.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.epam.entity.Event;
import com.epam.service.EventMessaging;

@Component
@Profile("kafka")
public class KafkaEventProducer implements EventMessaging {

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    @Override
    public void createEvent(Event event) {
        kafkaTemplate.send("create-event-request", event);
    }

    @Override
    public void updateEvent(Event event) {
        kafkaTemplate.send("update-event-request", event);
    }

    @Override
    public void deleteEvent(Event event) {
        kafkaTemplate.send("delete-event-request", event);
    }

}
