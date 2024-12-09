package dormapp.service;

import dormapp.entities.Event;

import java.util.List;

public interface EventService {
    Event getEventById(int id);
    List<Event> getAllEvents();
    void registerEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(int id);
}