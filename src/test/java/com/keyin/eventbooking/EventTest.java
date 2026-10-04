package com.keyin.eventbooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {

    @Test
    public void eventShouldNotBeFullWhenSpaceAvailable() {
        Event event = new Event("Small Halloween Party", LocalDate.of(2026, 10, 31), 2);

        Attendee attendee1 = new Attendee("Doug Graves", "doug.graves@example.com");

        event.registerAttendee(attendee1);

        Assertions.assertFalse(event.isFull());
    }

    @Test
    public void eventShouldBeFullWhenCapacityReached() {
        Event event = new Event("Small Halloween Party", LocalDate.of(2026, 10, 31), 2);

        Attendee attendee1 = new Attendee("Doug Graves", "doug.graves@example.com");
        Attendee attendee2 = new Attendee("Frank N. Stein", "frankn.stein@example.com");

        event.registerAttendee(attendee1);
        event.registerAttendee(attendee2);

        Assertions.assertTrue(event.isFull());
    }

    @Test
    public void preventDuplicateRegistration() {
        Event event = new Event("Annual Procrastinators Conference", LocalDate.of(2026, 11, 20), 300);

        Attendee attendee = new Attendee("Will Doit", "will.doit@example.com");
        Attendee duplicateAttendee = new Attendee("Will Doit", "will.doit@example.com");

        event.registerAttendee(attendee);

        Assertions.assertFalse(event.registerAttendee(duplicateAttendee));
    }

    @Test
    public void shouldNotRegisterAttendeeWhenAtCapacity() {
        Event event = new Event("Beagle Convention", LocalDate.of(2026, 12, 1), 2);

        Attendee attendee1 = new Attendee("Bark Ruffalo", "bark.ruffalo@example.com");
        Attendee attendee2 = new Attendee("Sarah Jessica Barker", "sarahjessica.barker@example.com");
        Attendee attendee3 = new Attendee("Ozzy Pawsborne", "ozzy.pawsborne@example.com");

        event.registerAttendee(attendee1);
        event.registerAttendee(attendee2);

        Assertions.assertFalse(event.registerAttendee(attendee3));
    }

    @Test
    public void shouldRegisterAttendeeWhenSpaceAvailable() {
        Event event = new Event("Beagle Convention", LocalDate.of(2026, 12, 1), 3);

        Attendee attendee1 = new Attendee("Bark Ruffalo", "bark.ruffalo@example.com");
        Attendee attendee2 = new Attendee("Sarah Jessica Barker", "sarahjessica.barker@example.com");
        Attendee attendee3 = new Attendee("Ozzy Pawsborne", "ozzy.pawsborne@example.com");

        event.registerAttendee(attendee1);
        event.registerAttendee(attendee2);

        Assertions.assertTrue(event.registerAttendee(attendee3));
    }
}
