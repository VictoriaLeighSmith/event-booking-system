package com.keyin.eventbooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventTest {

    // Capacity tests
    @Test
    public void notFullWhenSpaceAvailable() {
        Event event = new Event("Small Halloween Party", LocalDate.of(2026, 10, 31), 2);

        Attendee attendee1 = new Attendee("Doug Graves", "doug.graves@example.com");

        event.registerAttendee(attendee1);

        Assertions.assertFalse(event.isFull());
    }

    @Test
    public void fullWhenCapacityReached() {
        Event event = new Event("Small Halloween Party", LocalDate.of(2026, 10, 31), 2);

        Attendee attendee1 = new Attendee("Doug Graves", "doug.graves@example.com");
        Attendee attendee2 = new Attendee("Frank N. Stein", "frank.n.stein@example.com");

        event.registerAttendee(attendee1);
        event.registerAttendee(attendee2);

        Assertions.assertTrue(event.isFull());
    }

    @Test
    public void getsAvailableSpots() {
        Event event =  new Event("Small Halloween Party", LocalDate.of(2026, 10, 31), 2);

        Attendee attendee = new Attendee("Frank N. Stein", "frank.n.stein@example.com");
        event.registerAttendee(attendee);

        Assertions.assertEquals(1, event.getAvailableSpots());
    }

    // Registration tests
    @Test
    public void preventDuplicateRegistration() {
        Event event = new Event("Annual Procrastinators Conference", LocalDate.of(2026, 11, 20), 300);

        Attendee attendee = new Attendee("Will Doit", "will.doit@example.com");
        Attendee duplicateAttendee = new Attendee("Will Doit", "will.doit@example.com");

        event.registerAttendee(attendee);

        Assertions.assertFalse(event.registerAttendee(duplicateAttendee));
    }

    @Test
    public void rejectRegistrationAtCapacity() {
        Event event = new Event("Beagle Convention", LocalDate.of(2026, 12, 1), 2);

        Attendee attendee1 = new Attendee("Bark Ruffalo", "bark.ruffalo@example.com");
        Attendee attendee2 = new Attendee("Sarah Jessica Barker", "sarahjessica.barker@example.com");
        Attendee attendee3 = new Attendee("Ozzy Pawsborne", "ozzy.pawsborne@example.com");

        event.registerAttendee(attendee1);
        event.registerAttendee(attendee2);

        Assertions.assertFalse(event.registerAttendee(attendee3));
    }

    @Test
    public void registerAttendeeWhenSpaceAvailable() {
        Event event = new Event("Beagle Convention", LocalDate.of(2026, 12, 1), 3);

        Attendee attendee1 = new Attendee("Bark Ruffalo", "bark.ruffalo@example.com");
        Attendee attendee2 = new Attendee("Sarah Jessica Barker", "sarahjessica.barker@example.com");
        Attendee attendee3 = new Attendee("Ozzy Pawsborne", "ozzy.pawsborne@example.com");

        event.registerAttendee(attendee1);
        event.registerAttendee(attendee2);

        Assertions.assertTrue(event.registerAttendee(attendee3));
    }

    // Cancellation tests
    @Test
    public void cancelsRegisteredAttendee() {
        Event event = new Event("Annual Procrastinators Conference", LocalDate.of(2026, 11, 20), 300);

        Attendee attendee = new Attendee("Justin Time", "justin.time@example.com");
        event.registerAttendee(attendee);

        Assertions.assertTrue(event.cancelRegistration(attendee));
    }

    @Test
    public void doesNotCancelUnregisteredAttendee() {
        Event event = new Event("Annual Procrastinators Conference", LocalDate.of(2026, 11, 20), 300);

        Attendee attendee = new Attendee("Justin Time", "justin.time@example.com");

        Assertions.assertFalse(event.cancelRegistration(attendee));
    }

    @Test
    public void attendeeRemovedAfterCancellation() {
        Event event = new Event("Annual Procrastinators Conference", LocalDate.of(2026, 11, 20), 300);

        Attendee attendee = new Attendee("Justin Time", "justin.time@example.com");

        event.registerAttendee(attendee);
        event.cancelRegistration(attendee);

        Assertions.assertFalse(event.getAttendees().contains(attendee));
    }
}
