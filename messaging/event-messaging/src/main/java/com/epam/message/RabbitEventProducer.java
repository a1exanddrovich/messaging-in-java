package com.epam.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.epam.entity.Event;
import com.epam.service.EventMessaging;

@Component
@Profile("rabbit")
public class RabbitEventProducer implements EventMessaging {

    @Autowired
    private RabbitTemplate template;

    @Override
    public void createEvent(Event event) {
        template.convertAndSend("exchange-topic", "create-binding", event);
    }

    @Override
    public void updateEvent(Event event) {
        template.convertAndSend("exchange-topic", "update-binding", event);
    }

    @Override
    public void deleteEvent(Event event) {
        template.convertAndSend("exchange-topic", "delete-binding", event);
    }

}
