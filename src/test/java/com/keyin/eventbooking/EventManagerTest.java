package com.keyin.eventbooking;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EventManagerTest {

    // EventManager tests
    @Test
    public void addsEvent() {
        Event event = new Event("Silly Event",  LocalDate.of(2026, 11, 20), 300);
        EventManager eventManager = new EventManager();

        Assertions.assertTrue(eventManager.addEvent(event));
    }

    @Test
    public void findsEventById() {
        Event event = new Event("Silly Event", LocalDate.of(2026, 11, 20), 300);
        EventManager eventManager = new EventManager();

        eventManager.addEvent(event);

        Assertions.assertEquals(event, eventManager.findEvent(event.getId()));
    }

    @Test
    public void doesNotFindEventThatDoesNotExist() {
        Event event = new Event("Silly Event", LocalDate.of(2026, 11, 20), 300);
        EventManager eventManager = new EventManager();

        eventManager.addEvent(event);

        Assertions.assertNull(eventManager.findEvent(-1));
    }

    @Test
    public void deletesEvent() {
        Event event = new Event("Silly Event", LocalDate.of(2026, 11, 20), 300);
        EventManager eventManager = new EventManager();

        eventManager.addEvent(event);
        Assertions.assertTrue(eventManager.deleteEvent(event.getId()));
    }
}
