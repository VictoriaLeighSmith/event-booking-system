package com.keyin.eventbooking;

import java.util.ArrayList;

public class EventManager {
    private ArrayList<Event> events;

    public EventManager() {
        this.events = new ArrayList<>();
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }

    public Event findEvent(int eventId) {
        for (Event event : events) {
            if (event.getId() == eventId) {
                return event;
            }
        }

        return null;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public boolean deleteEvent(int eventId) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId() == eventId) {
                events.remove(i);
                return true;
            }
        }

        return false;
    }
}
