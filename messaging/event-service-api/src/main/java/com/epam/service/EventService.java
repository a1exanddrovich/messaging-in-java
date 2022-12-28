package com.epam.service;

import java.util.List;

import com.epam.entity.Event;

public interface EventService {

    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    Event getEvent(Long id);
    Event deleteEvent(Long id);
    List<Event> getAllEvents();

    void updateEventMessage(Long id, Event event);
    void createEventMessage(Event event);
    void deleteEventMessage(Long id);

}
