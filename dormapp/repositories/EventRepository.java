package dormapp.repositories;

import dormapp.entities.Event;

import java.util.List;

public interface EventRepository {
    Event getById(int id);
    List<Event> getAll();
    void save(Event event);
    void update(Event event);
    void delete(int id);
}