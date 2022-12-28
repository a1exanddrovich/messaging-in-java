package com.epam.service;

import com.epam.entity.Event;

public interface EventMessaging {

    void createEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(Event event);

}
