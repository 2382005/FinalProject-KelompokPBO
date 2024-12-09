package dormapp;

import dormapp.config.Database;
import dormapp.repositories.UserRepository;
import dormapp.repositories.UserRepositoryDbImpl;
import dormapp.service.UserService;
import dormapp.service.UserServiceImpl;
import dormapp.views.AdminView;
import dormapp.views.UserView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Menginisialisasi koneksi database
        Database database = new Database("dormapp", "root", "", "localhost", "3306");
        database.setup();

        // Menginisialisasi repositori pengguna
        UserRepository userRepository = new UserRepositoryDbImpl(database);

        // Menginisialisasi layanan pengguna
        UserService userService = new UserServiceImpl(userRepository);

        // Menampilkan menu berdasarkan peran pengguna
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Dorm Management System!");
        System.out.print("Are you an Admin or User? (admin/user): ");
        String role = scanner.nextLine();

        if (role.equalsIgnoreCase("admin")) {
            AdminView adminView = new AdminView(userService);
            adminView.showMenu();
        } else if (role.equalsIgnoreCase("user")) {
            UserView userView = new UserView(userService);
            userView.showMenu();
        } else {
            System.out.println("Invalid role! Please enter 'admin' or 'user'.");
        }

        scanner.close();
    }
}