package dormapp.repositories;

import dormapp.entities.Feedback;

import java.util.List;

public interface FeedbackRepository {
    Feedback getById(int id);
    List<Feedback> getAll();
    void save(Feedback feedback);
    void update(Feedback feedback);
    void delete(int id);
}