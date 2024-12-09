package dormapp;

import dormapp.config.Database;
import dormapp.repositories.UserRepository;
import dormapp.repositories.UserRepositoryDbImpl;
import dormapp.service.UserService;
import dormapp.service.UserServiceImpl;
import dormapp.views.AdminView;
import dormapp.views.UserView;
import dormapp.views.AttendanceView;
import dormapp.views.EventView;
import dormapp.views.FeedbackView;
import dormapp.repositories.AttendanceRepositoryDbImpl;
import dormapp.repositories.EventRepositoryDbImpl;
import dormapp.repositories.FeedbackRepositoryDbImpl;
import dormapp.service.AttendanceServiceImpl;
import dormapp.service.EventServiceImpl;
import dormapp.service.FeedbackServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialize database connection
        Database database = new Database("dormapp4", "root", "", "localhost", "3306");
        database.setup();

        // Initialize user repository and service
        UserRepository userRepository = new UserRepositoryDbImpl(database);
        UserService userService = new UserServiceImpl(userRepository);

        // Display menu based on user role
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Dorm Management System!");
        System.out.print("Are you an Admin or User? (admin/user): ");
        String role = scanner.nextLine().trim().toLowerCase();

        switch (role) {
            case "admin":
                handleAdminMenu(userService, database);
                break;
            case "user":
                handleUserMenu(userService, database);
                break;
            default:
                System.out.println("Invalid role! Please enter 'admin' or 'user'.");
                break;
        }

        scanner.close();
    }

    private static void handleAdminMenu(UserService userService, Database database) {
        AdminView adminView = new AdminView(userService);
        adminView.showMenu();

        // Initialize and show menus for Attendance, Event, and Feedback
        AttendanceView attendanceView = new AttendanceView(new AttendanceServiceImpl(new AttendanceRepositoryDbImpl(database)));
        EventView eventView = new EventView(new EventServiceImpl(new EventRepositoryDbImpl(database)));
        FeedbackView feedbackView = new FeedbackView(new FeedbackServiceImpl(new FeedbackRepositoryDbImpl(database)));

        attendanceView.showMenu();
        eventView.showMenu();
        feedbackView.showMenu();
    }

    private static void handleUserMenu(UserService userService, Database database) {
        UserView userView = new UserView(userService);
        userView.showMenu();

        // Initialize and show menus for Attendance and Event
        AttendanceView attendanceView = new AttendanceView(new AttendanceServiceImpl(new AttendanceRepositoryDbImpl(database)));
        EventView eventView = new EventView(new EventServiceImpl(new EventRepositoryDbImpl(database)));

        attendanceView.showMenu();
        eventView.showMenu();
    }
}