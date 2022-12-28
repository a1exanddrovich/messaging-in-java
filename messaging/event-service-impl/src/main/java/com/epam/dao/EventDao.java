package com.epam.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.epam.entity.Event;

@Component
public class EventDao {

    private final Map<Long, Event> events = new HashMap<>();

    public Event add(Event event) {
        events.put(event.getEventId(), event);
        return events.get(event.getEventId());
    }

    public Event updateById(Long id, Event event) {
        events.put(id, event);
        return events.get(event.getEventId());
    }

    public Event findById(Long id) {
        return events.get(id);
    }

    public Event deleteById(Long id) {
        Event event = events.get(id);
        events.remove(event.getEventId());
        return event;
    }

    public List<Event> findAll() {
        return new ArrayList<>(events.values());
    }

}
