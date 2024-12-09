package dormapp.entities;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String name;
    private LocalDateTime eventDate;

    public Event(int id, String name, LocalDateTime eventDate) {
        this.id = id;
        this.name = name;
        this.eventDate = eventDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }
}