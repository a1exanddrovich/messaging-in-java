package com.epam.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.epam.entity.Event;
import com.epam.service.EventMessaging;

@Component
@Profile("activemq")
public class ActiveEventProducer implements EventMessaging {

    @Autowired
    private JmsTemplate template;

    @Override
    public void createEvent(Event event) {
        template.convertAndSend("create-event-request", event);
    }

    @Override
    public void updateEvent(Event event) {
        template.convertAndSend("update-event-request", event);
    }

    @Override
    public void deleteEvent(Event event) {
        template.convertAndSend("delete-event-request", event);
    }

}
