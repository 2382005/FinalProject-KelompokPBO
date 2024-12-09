package dormapp.service;

import dormapp.entities.Event;
import dormapp.repositories.EventRepository;

import java.util.List;

public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getEventById(int id) {
        return eventRepository.getById(id);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.getAll();
    }

    @Override
    public void registerEvent(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void updateEvent(Event event) {
        eventRepository.update(event);
    }

    @Override
    public void deleteEvent(int id) {
        eventRepository.delete(id);
    }
}