package dormapp.service;

import dormapp.entities.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback getFeedbackById(int id);
    List<Feedback> getAllFeedbacks();
    void registerFeedback(Feedback feedback);
    void updateFeedback(Feedback feedback);
    void deleteFeedback(int id);
}