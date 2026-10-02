package com.keyin.eventbooking;

public class Attendee {
    // May need to add to this class. Just setting it up so that I can properly test the event class.
    private String name;
    private String email;

    public Attendee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
