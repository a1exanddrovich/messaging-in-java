package com.epam.message;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.epam.entity.Event;
import com.epam.service.EventService;

@Component
@Profile("kafka")
public class KafkaEventListener {

    @Autowired
    private EventService eventService;

    @KafkaListener(topics = "create-event-request", groupId = "groupId")
    public void createEvent(List<Event> event) {
        event.forEach(e -> eventService.createEventMessage(e));
    }

    @KafkaListener(topics = "update-event-request", groupId = "groupId")
    public void updateEvent(List<Event> events) {
        events.forEach(event -> eventService.updateEventMessage(event.getEventId(), event));
    }

    @KafkaListener(topics = "delete-event-request", groupId = "groupId")
    public void deleteEvent(List<Event> eventIds) {
        eventIds.forEach(event -> eventService.deleteEventMessage(event.getEventId()));
    }

}
