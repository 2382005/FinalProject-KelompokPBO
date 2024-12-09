package dormapp.views;

import dormapp.entities.Attendance;
import dormapp.service.AttendanceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AttendanceView {
    private final AttendanceService attendanceService;
    private final Scanner scanner = new Scanner(System.in);

    public AttendanceView(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== Attendance Menu ===");
            System.out.println("1. View All Attendances");
            System.out.println("2. Register Attendance");
            System.out.println("3. Update Attendance");
            System.out.println("4. Delete Attendance");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAllAttendances();
                case 2 -> registerAttendance();
                case 3 -> updateAttendance();
                case 4 -> deleteAttendance();
                case 5 -> System.out.println("Exiting Attendance Menu...");
                default -> System.out.println("Invalid option, please try again.");
            }
        } while (choice != 5);
    }

    private void viewAllAttendances() {
        List<Attendance> attendances = attendanceService.getAllAttendances();
        System.out.println("All Attendances:");
        for (Attendance attendance : attendances) {
            System.out.println("ID: " + attendance.getId() + ", User ID: " + attendance.getUserId() + ", Timestamp: " + attendance.getTimestamp());
        }
    }

    private void registerAttendance() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        Attendance attendance = new Attendance(0, userId, LocalDateTime.now());
        attendanceService.registerAttendance(attendance);
        System.out.println("Attendance registered successfully.");
    }

    private void updateAttendance() {
        System.out.print("Enter Attendance ID to update: ");
        int id = scanner.nextInt();
        Attendance attendance = attendanceService.getAttendanceById(id);
        if (attendance != null) {
            System.out.print("Enter new User ID: ");
            int userId = scanner.nextInt();
            attendance = new Attendance(id, userId, LocalDateTime.now());
            attendanceService.updateAttendance(attendance);
            System.out.println("Attendance updated successfully.");
        } else {
            System.out.println("Attendance not found.");
        }
    }

    private void deleteAttendance() {
        System.out.print("Enter Attendance ID to delete: ");
        int id = scanner.nextInt();
        attendanceService.deleteAttendance(id);
        System.out.println("Attendance deleted successfully.");
    }
}