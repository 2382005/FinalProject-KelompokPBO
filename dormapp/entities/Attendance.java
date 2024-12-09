package dormapp.entities;

public class Attendance {
    private int id;
    private int userId;
    private int eventId;
    private boolean isPresent;

    public Attendance(int id, int userId, int eventId, boolean isPresent) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.isPresent = isPresent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
