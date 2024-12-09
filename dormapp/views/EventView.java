package dormapp.views;

import dormapp.entities.Event;
import dormapp.service.EventService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class EventView {
    private final EventService eventService;
    private final Scanner scanner = new Scanner(System.in);

    public EventView(EventService eventService) {
        this.eventService = eventService;
    }

    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== Event Menu ===");
            System.out.println("1. View All Events");
            System.out.println("2. Register Event");
            System.out.println("3. Update Event");
            System.out.println("4. Delete Event");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewAllEvents();
                case 2 -> registerEvent();
                case 3 -> updateEvent();
                case 4 -> deleteEvent();
                case 5 -> System.out.println("Exiting Event Menu...");
                default -> System.out.println("Invalid option, please try again.");
            }
        } while (choice != 5);
    }

    private void viewAllEvents() {
        List<Event> events = eventService.getAllEvents();
        System.out.println("All Events:");
        for (Event event : events) {
            System.out.println("ID: " + event.getId() + ", Name: " + event.getName() + ", Event Date: " + event.getEventDate());
        }
    }

    private void registerEvent() {
        System.out.print("Enter Event Name: ");
        String name = scanner.nextLine();
        Event event = new Event(0, name, LocalDateTime.now().plusDays(1)); // Set event date to tomorrow
        eventService.registerEvent(event);
        System.out.println("Event registered successfully.");
    }

    private void updateEvent() {
        System.out.print("Enter Event ID to update: ");
        int id = scanner.nextInt();
        Event event = eventService.getEventById(id);
        if (event != null) {
            System.out.print("Enter new Event Name: ");
            String name = scanner.next();
            event = new Event(id, name, LocalDateTime.now().plusDays(1)); // Update event date to tomorrow
            eventService.updateEvent(event);
            System.out.println("Event updated successfully.");
        } else {
            System.out.println("Event not found.");
        }
    }

    private void deleteEvent() {
        System.out.print("Enter Event ID to delete: ");
        int id = scanner.nextInt();
        eventService.deleteEvent(id);
        System.out.println("Event deleted successfully.");
    }
}