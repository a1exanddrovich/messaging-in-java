package com.epam.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.epam.entity.Event;
import com.epam.service.EventService;

@Component
@Profile("rabbit")
public class RabbitEventListener {

    @Autowired
    private EventService eventService;

    @RabbitListener(queues = "create-event-request", messageConverter = "Jackson2JsonMessageConverter")
    public void createEvent(Event event) {
        eventService.createEventMessage(event);
    }

    @RabbitListener(queues = "update-event-request", messageConverter = "Jackson2JsonMessageConverter")
    public void updateEvent(Event event) {
        eventService.updateEventMessage(event.getEventId(), event);
    }

    @RabbitListener(queues = "delete-event-request", messageConverter = "Jackson2JsonMessageConverter")
    public void deleteEvent(Event event) {
        eventService.deleteEventMessage(event.getEventId());
    }

}
