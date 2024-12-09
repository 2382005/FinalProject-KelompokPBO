package dormapp.views;

import dormapp.entities.User;
import dormapp.service.UserService;

import java.util.Scanner;

public class AdminView {
    private final UserService userService; // Menggunakan UserService yang di-inject
    private final Scanner scanner = new Scanner(System.in);

    // Konstruktor menerima UserService untuk mendukung dependency injection
    public AdminView(UserService userService) {
        this.userService = userService; // Menginisialisasi userService
    }

    public void showMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. View All Users");
        System.out.println("2. Add User");
        System.out.println("3. Delete User");
        System.out.println("4. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (choice) {
            case 1 -> viewAllUsers();
            case 2 -> addUser ();
            case 3 -> deleteUser ();
            case 4 -> System.out.println("Exiting Admin Menu...");
            default -> System.out.println("Invalid choice!");
        }
    }

    private void viewAllUsers() {
        System.out.println("All Users:");
        for (User  user : userService.getAllUsers()) {
            System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Is Admin: " + user.isAdmin());
        }
    }

    private void addUser () {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Is Admin (true/false): ");
        boolean isAdmin = scanner.nextBoolean();

        User newUser  = new User(0, username, password, isAdmin);
        userService.register(newUser );
        System.out.println("User  added successfully.");
    }

    private void deleteUser () {
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        userService.deleteUser (userId);
        System.out.println("User  deleted successfully.");
    }
}