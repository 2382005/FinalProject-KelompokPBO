package dormapp.entities;

import java.time.LocalDateTime;

public class Attendance {
    private int id;
    private int userId;
    private LocalDateTime timestamp;

    public Attendance(int id, int userId, LocalDateTime timestamp) {
        this.id = id;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}