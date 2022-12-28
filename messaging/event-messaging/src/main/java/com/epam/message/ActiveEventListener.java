package com.epam.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.epam.entity.Event;
import com.epam.service.EventService;

@Component
@Profile("activemq")
public class ActiveEventListener {

    @Autowired
    private EventService eventService;

    @JmsListener(destination = "create-event-request")
    public void createEvent(Event event) {
        eventService.createEventMessage(event);
    }

    @JmsListener(destination = "update-event-request")
    public void updateEvent(Event event) {
        eventService.updateEventMessage(event.getEventId(), event);
    }

    @JmsListener(destination = "delete-event-request")
    public void deleteEvent(Event event) {
        eventService.deleteEventMessage(event.getEventId());
    }

}
