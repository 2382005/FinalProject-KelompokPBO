package dormapp.views;

import dormapp.entities.Feedback;
import dormapp.service.FeedbackService;

import java.util.List;
import java.util.Scanner;

public class FeedbackView {
    private final FeedbackService feedbackService;
    private final Scanner scanner = new Scanner(System.in);

    public FeedbackView(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== Feedback Menu ===");
            System.out.println("1. View All Feedbacks");
            System.out.println("2. Register Feedback");
            System.out.println("3. Update Feedback");
            System.out.println("4. Delete Feedback");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAllFeedbacks();
                case 2 -> registerFeedback();
                case 3 -> updateFeedback();
                case 4 -> deleteFeedback();
                case 5 -> System.out.println("Exiting Feedback Menu...");
                default -> System.out.println("Invalid option, please try again.");
            }
        } while (choice != 5);
    }

    private void viewAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        System.out.println("All Feedbacks:");
        for (Feedback feedback : feedbacks) {
            System.out.println("ID: " + feedback.getId() + ", User ID: " + feedback.getUserId() + ", Comment: " + feedback.getComment());
        }
    }

    private void registerFeedback() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter Comment: ");
        String comment = scanner.next();
        Feedback feedback = new Feedback(0, userId, comment);
        feedbackService.registerFeedback(feedback);
        System.out.println("Feedback registered successfully.");
    }

    private void updateFeedback() {
        System.out.print("Enter Feedback ID to update: ");
        int id = scanner.nextInt();
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            System.out.print("Enter new Comment: ");
            String comment = scanner.next();
            feedback = new Feedback(id, feedback.getUserId(), comment);
            feedbackService.updateFeedback(feedback);
            System.out.println("Feedback updated successfully.");
        } else {
            System.out.println("Feedback not found.");
        }
    }

    private void deleteFeedback() {
        System.out.print("Enter Feedback ID to delete: ");
        int id = scanner.nextInt();
        feedbackService.deleteFeedback(id);
        System.out.println("Feedback deleted successfully.");
    }
}