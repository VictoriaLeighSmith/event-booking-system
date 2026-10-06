package com.keyin.eventbooking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventManager eventManager = new EventManager();

        boolean displayMenu = true;

        while (displayMenu) {
            System.out.println();
            System.out.println("EVENT BOOKING SYSTEM");
            System.out.println();
            System.out.println("1. Create Event");
            System.out.println("2. View Event");
            System.out.println("3. View All Events");
            System.out.println("4. Delete Event");
            System.out.println("5. Register Attendee");
            System.out.println("6. Cancel Registration");
            System.out.println("7. Exit");
            System.out.println();
            System.out.print("Please enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a number from 1-7.");
                scanner.nextLine();
                continue;
            }

            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    createEvent(scanner, eventManager);
                    break;
                case 2:
                    viewEvent(scanner, eventManager);
                    break;
                case 3:
                    viewAllEvents(eventManager);
                    break;
                case 4:
                    deleteEvent(scanner, eventManager);
                    break;
                case 5:
                    registerAttendee(scanner, eventManager);
                    break;
                case 6:
                    cancelRegistration(scanner, eventManager);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    displayMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    private static void createEvent(Scanner scanner, EventManager eventManager) {
        System.out.print("Enter event name: ");
        String eventName = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eventDate = null;
        boolean isValidDate = false;

        while (!isValidDate) {
            System.out.print("Enter event date (YYYY-MM-DD): ");
            String eventDateInput = scanner.nextLine();

            try {
                eventDate = LocalDate.parse(eventDateInput, formatter);
                isValidDate = true;
            } catch (DateTimeParseException e) {
                System.out.println();
                System.out.println("Invalid date format. Please enter a valid date.");
            }
        }

        int eventCapacity = 0;
        boolean isValidCapacity = false;

        while (!isValidCapacity) {
            System.out.print("Enter event capacity: ");
            String eventCapacityInput = scanner.nextLine();

            try {
                eventCapacity = Integer.parseInt(eventCapacityInput);

                if (eventCapacity > 0) {
                    isValidCapacity = true;
                } else {
                    System.out.println();
                    System.out.println("Event capacity must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid event capacity. Please enter a valid number.");
            }
        }

        Event event = new Event(eventName, eventDate, eventCapacity);

        if (eventManager.addEvent(event)) {
            System.out.println();
            System.out.println("Event has been added successfully!");
        } else {
            System.out.println();
            System.out.println("Unable to add event. Please try again.");
        }
    }

    private static void viewEvent(Scanner scanner, EventManager eventManager) {
        int eventId = getEventId(scanner);
        Event event = eventManager.findEvent(eventId);

        if (event == null) {
            System.out.println();
            System.out.println("Event not found. Please try again.");
            return;
        }

        System.out.println();
        System.out.println(event.getName().toUpperCase() + " DETAILS");
        System.out.println();
        System.out.println("Event ID: " + event.getId());
        System.out.println("Event date: " + event.getDate());
        System.out.println("Event capacity: " + event.getCapacity());
        System.out.println("Event spots available: " + event.getAvailableSpots());

        if (event.getAttendees().isEmpty()) {
            System.out.println("Event attendees: None");
        } else {
            System.out.println("Event attendees: ");

            for (Attendee attendee : event.getAttendees()) {
                System.out.println("- Name: " + attendee.getName());
                System.out.println("  Email: " + attendee.getEmail());
                System.out.println();
            }
        }
    }

    private static void viewAllEvents(EventManager eventManager) {
        System.out.println("ALL EVENTS");
        System.out.println();

        ArrayList<Event> events = eventManager.getEvents();

        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        for (Event event : events) {
            System.out.println("Event ID: " + event.getId());
            System.out.println("Event name: "  + event.getName());
            System.out.println("Event date: " + event.getDate());
            System.out.println("Event capacity: " + event.getCapacity());
            System.out.println("Event spots available: " + event.getAvailableSpots());

            if (event.getAttendees().isEmpty()) {
                System.out.println("Event attendees: None");
            } else {
                System.out.println("Event attendees: ");

                for (Attendee attendee : event.getAttendees()) {
                    System.out.println("- Name: " + attendee.getName());
                    System.out.println("  Email: " + attendee.getEmail());
                    System.out.println();
                }
            }

            System.out.println();
        }
    }

    private static void deleteEvent(Scanner scanner, EventManager eventManager) {
        boolean displayDeleteMenu = true;

        while (displayDeleteMenu) {
            System.out.println("DELETE AN EVENT");
            System.out.println();

            if (eventManager.getEvents().isEmpty()) {
                System.out.println("No events found.");
                break;
            }

            for (Event event : eventManager.getEvents()) {
                System.out.println("ID: " + event.getId() + " - " + event.getName());
            }

            System.out.println();
            System.out.print("Please enter the event ID to delete: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid choice. Please enter an event ID from the menu.");
                scanner.nextLine();
                System.out.print("Please enter the event ID to delete: ");
            }

            int eventId = scanner.nextInt();
            scanner.nextLine();

            if (eventManager.deleteEvent(eventId)) {
                System.out.println();
                System.out.println("Event has been deleted successfully!");
                displayDeleteMenu = false;
            } else {
                System.out.println();
                System.out.println("Unable to delete event. Please try again.");
            }

        }
    }

    private static void registerAttendee(Scanner scanner, EventManager eventManager) {
        int eventId = getEventId(scanner);
        Event event = eventManager.findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found. Please try again.");
            return;
        }

        System.out.print("Enter attendee name: ");
        String attendeeName = scanner.nextLine();

        System.out.print("Enter attendee email: ");
        String attendeeEmail = scanner.nextLine();

        Attendee attendee = new Attendee(attendeeName, attendeeEmail);

        if (event.registerAttendee(attendee)) {
            System.out.println("Successfully registered!");
        } else {
            System.out.println("Unable to register attendee. Please try again.");
        }
    }

    private static void cancelRegistration(Scanner scanner, EventManager eventManager) {
        int eventId = getEventId(scanner);
        Event event = eventManager.findEvent(eventId);

        if (event == null) {
            System.out.println("Event not found. Please try again.");
            return;
        }

        System.out.print("Enter attendee email to cancel registration: ");
        String attendeeEmail = scanner.nextLine();

        Attendee foundAttendee = null;

        for (Attendee attendee : event.getAttendees()) {
            if (attendee.getEmail().equals(attendeeEmail)) {
                foundAttendee = attendee;
                break;
            }
        }

        if (foundAttendee == null) {
            System.out.println("Attendee not found. Please try again.");
            return;
        }

        if (event.cancelRegistration(foundAttendee)) {
            System.out.println("Successfully cancelled registration!");
        } else {
            System.out.println("Unable to cancel registration. Please try again.");
        }
    }

    private static int getEventId(Scanner scanner) {
        System.out.print("Enter event ID: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Invalid event ID. Please enter a valid number.");
            scanner.next();
            System.out.print("Enter event ID: ");
        }

        int eventId = scanner.nextInt();
        scanner.nextLine();

        return eventId;
    }
}
