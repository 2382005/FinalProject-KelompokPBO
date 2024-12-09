package dormapp.entities;

public class Feedback {
    private int id;
    private int userId;
    private String comment;

    public Feedback(int id, int userId, String comment) {
        this.id = id;
        this.userId = userId;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }
}