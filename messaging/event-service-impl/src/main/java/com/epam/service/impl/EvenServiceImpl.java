package com.epam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dao.EventDao;
import com.epam.entity.Event;
import com.epam.service.EventMessaging;
import com.epam.service.EventService;

@Component
public class EvenServiceImpl implements EventService {

    @Autowired
    private EventDao eventDao;
    @Autowired(required = false)
    private EventMessaging eventMessaging;

    @Override
    public Event createEvent(Event event) {
        eventMessaging.createEvent(event);
        return event;
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        eventMessaging.updateEvent(event);
        return event;
    }

    @Override
    public Event getEvent(Long id) {
        return eventDao.findById(id);
    }

    @Override
    public Event deleteEvent(Long id) {
        Event event = eventDao.findById(id);
        eventMessaging.deleteEvent(event);
        return null;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.findAll();
    }

    @Override
    public void updateEventMessage(Long id, Event event) {
        eventDao.updateById(id, event);
    }

    @Override
    public void createEventMessage(Event event) {
        eventDao.add(event);
    }

    @Override
    public void deleteEventMessage(Long id) {
        eventDao.deleteById(id);
    }

}
