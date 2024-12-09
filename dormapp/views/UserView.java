package dormapp.views;

import dormapp.entities.User;
import dormapp.service.UserService;

import java.util.Scanner;

public class UserView {
    private final UserService userService;

    public UserView(UserService userService) {
        this.userService = userService;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewProfile();
                case 2 -> updateProfile();
                case 3 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option, please try again.");
            }
        } while (choice != 3);
    }

    private void viewProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = userService.getUserByUsername(username);

        if (user != null) {
            System.out.println("\n=== Profile Details ===");
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());
        } else {
            System.out.println("User  not found!");
        }
    }

    private void updateProfile() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        User user = userService.getUserByUsername(username);

        if (user != null) {
            System.out.print("Enter new username: ");
            String newUsername = scanner.nextLine();
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();

            user.setUsername(newUsername);
            user.setPassword(newPassword);
            userService.updateUser (user);

            System.out.println("Profile updated successfully!");
        } else {
            System.out.println("User  not found!");
        }
    }
}