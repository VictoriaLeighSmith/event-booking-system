package com.keyin.eventbooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {

    @Test
    public void eventShouldNotBeFullWhenSpaceAvailable() {
        Event event = new Event("Small Halloween Party", LocalDate.of(2026, 10, 31), 2);
        Attendee partyGuy = new Attendee("Party Guy", "party.guy@example.com");

        event.registerAttendee(partyGuy);

        Assertions.assertFalse(event.isFull());
    }

    @Test
    public void eventShouldBeFullWhenCapacityReached() {
        Event event = new Event("Small Halloween Party", LocalDate.of(2026, 10, 31), 2);
        Attendee partyGuy = new Attendee("Party Guy", "party.guy@example.com");
        Attendee partyGirl = new Attendee("Party Girl", "party.girl@example.com");

        event.registerAttendee(partyGuy);
        event.registerAttendee(partyGirl);

        Assertions.assertTrue(event.isFull());
    }
}
