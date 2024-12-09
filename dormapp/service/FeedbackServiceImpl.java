package dormapp.service;

import dormapp.entities.Feedback;
import dormapp.repositories.FeedbackRepository;

import java.util.List;

public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback getFeedbackById(int id) {
        return feedbackRepository.getById(id);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.getAll();
    }

    @Override
    public void registerFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        feedbackRepository.update(feedback);
    }

    @Override
    public void deleteFeedback(int id) {
        feedbackRepository.delete(id);
    }
}