package dormapp.entities;

public class Feedback {
    private int id;
    private int userId;
    private int eventId;
    private String content;

    public Feedback(int id, int userId, int eventId, String content) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
