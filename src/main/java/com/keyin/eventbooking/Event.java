package com.keyin.eventbooking;

import java.time.LocalDate;
import java.util.ArrayList;

public class Event {
    private String name;
    private LocalDate date;
    private int capacity;
    private ArrayList<Attendee> attendees;

    public Event(String name, LocalDate date, int capacity) {
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Attendee> getAttendees() {
        return attendees;
    }

    public boolean registerAttendee(Attendee attendee) {
        if (isFull() || isRegistered(attendee)) {
            return false;
        }

        return attendees.add(attendee);
    }

    public boolean cancelRegistration(Attendee attendee) {
        return attendees.remove(attendee);
    }

    public boolean isFull() {
        return attendees.size() == capacity;
    }

    public int getAvailableSpots() {
        return capacity - attendees.size();
    }

    public boolean isRegistered(Attendee attendee) {
        return attendees.contains(attendee);
    }
}
