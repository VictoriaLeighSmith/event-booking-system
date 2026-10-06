package com.keyin.eventbooking;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
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
                        String eventCapacityInput  = scanner.nextLine();

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

                    break;
                case 2:
                    System.out.print("Enter event ID: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid event ID. Please enter a valid number.");
                        scanner.next();
                        System.out.print("Enter event ID: ");
                    }

                    int eventIdInput = scanner.nextInt();
                    scanner.nextLine();

                    Event displayedEvent = eventManager.findEvent(eventIdInput);

                    if (displayedEvent == null) {
                        System.out.println();
                        System.out.println("Event not found. Please try again.");
                        break;
                    }

                    System.out.println();
                    System.out.println(displayedEvent.getName().toUpperCase() + " DETAILS");
                    System.out.println();
                    System.out.println("Event ID: " + displayedEvent.getId());
                    System.out.println("Event date: " + displayedEvent.getDate());
                    System.out.println("Event capacity: " + displayedEvent.getCapacity());
                    System.out.println("Event spots available: " + displayedEvent.getAvailableSpots());

                    if (displayedEvent.getAttendees().isEmpty()) {
                        System.out.println("Event attendees: None");
                    } else {
                        System.out.println("Event attendees: ");

                        for (Attendee attendee : displayedEvent.getAttendees()) {
                            System.out.println("- Name: " + attendee.getName());
                            System.out.println("  Email: " + attendee.getEmail());
                            System.out.println();
                        }
                    }

                    break;
                case 3:
                    System.out.println("ALL EVENTS");
                    System.out.println();

                    ArrayList<Event> registeredEvents = eventManager.getEvents();

                    if (registeredEvents.isEmpty()) {
                        System.out.println("No events found.");
                        break;
                    }

                    for (Event registeredEvent : registeredEvents) {
                        System.out.println("Event ID: " + registeredEvent.getId());
                        System.out.println("Event name: "  + registeredEvent.getName());
                        System.out.println("Event date: " + registeredEvent.getDate());
                        System.out.println("Event capacity: " + registeredEvent.getCapacity());
                        System.out.println("Event spots available: " + registeredEvent.getAvailableSpots());

                        if (registeredEvent.getAttendees().isEmpty()) {
                            System.out.println("Event attendees: None");
                        } else {
                            System.out.println("Event attendees: ");

                            for (Attendee attendee : registeredEvent.getAttendees()) {
                                System.out.println("- Name: " + attendee.getName());
                                System.out.println("  Email: " + attendee.getEmail());
                                System.out.println();
                            }
                        }

                        System.out.println();
                    }

                    break;
                case 4:
                    boolean displayDeleteMenu = true;

                    while (displayDeleteMenu) {
                        System.out.println("DELETE AN EVENT");
                        System.out.println();

                        if (eventManager.getEvents().isEmpty()) {
                            System.out.println("No events found.");
                            break;
                        }

                        for (Event eventToDelete : eventManager.getEvents()) {
                            System.out.println("ID: " + eventToDelete.getId() + " - " + eventToDelete.getName());
                        }

                        System.out.println();
                        System.out.print("Please enter the event ID to delete: ");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid choice. Please enter an event ID from the menu.");
                            scanner.nextLine();
                            System.out.print("Please enter the event ID to delete: ");
                        }

                        int userDeleteChoice = scanner.nextInt();
                        scanner.nextLine();

                        if (eventManager.deleteEvent(userDeleteChoice)) {
                            System.out.println();
                            System.out.println("Event has been deleted successfully!");
                            displayDeleteMenu = false;
                        } else {
                            System.out.println();
                            System.out.println("Unable to delete event. Please try again.");
                        }

                    }

                    break;
                case 5:
                    int validEventIdInput;

                    System.out.print("Enter event ID: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid event ID. Please enter a valid number.");
                        scanner.next();
                        System.out.print("Enter event ID: ");
                    }

                    validEventIdInput = scanner.nextInt();
                    scanner.nextLine();

                    Event eventToRegister = eventManager.findEvent(validEventIdInput);

                    if (eventToRegister == null) {
                        System.out.println("Event not found. Please try again.");
                        break;
                    }

                    System.out.print("Enter attendee name: ");
                    String attendeeNameInput = scanner.nextLine();

                    System.out.print("Enter attendee email: ");
                    String attendeeEmailInput = scanner.nextLine();

                    Attendee attendee  = new Attendee(attendeeNameInput, attendeeEmailInput);

                    if (eventToRegister.registerAttendee(attendee)) {
                        System.out.println("Successfully registered!");
                    } else {
                        System.out.println("Unable to register attendee. Please try again.");
                    }

                    break;
                case 6:
                    System.out.print("Enter event ID: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid event ID. Please enter a valid number.");
                        scanner.next();
                        System.out.print("Enter event ID: ");
                    }

                    int eventId = scanner.nextInt();
                    scanner.nextLine();

                    Event foundEvent = eventManager.findEvent(eventId);

                    if (foundEvent == null) {
                        System.out.println("Event not found. Please try again.");
                        break;
                    }

                    System.out.print("Enter attendee email to cancel registration: ");
                    String attendeeEmail = scanner.nextLine();

                    Attendee foundAttendee = null;

                    for (Attendee eventAttendee : foundEvent.getAttendees()) {
                        if (eventAttendee.getEmail().equals(attendeeEmail)) {
                            foundAttendee = eventAttendee;
                            break;
                        }
                    }

                    if  (foundAttendee == null) {
                        System.out.println("Attendee not found. Please try again.");
                        break;
                    }

                    if (foundEvent.cancelRegistration(foundAttendee)) {
                        System.out.println("Successfully cancelled registration!");
                    } else {
                        System.out.println("Unable to cancel registration. Please try again.");
                    }

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
}
