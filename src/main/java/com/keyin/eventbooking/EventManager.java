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

    public Event findEvent(String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return event;
            }
        }

        return null;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public boolean deleteEvent(String eventName) {
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getName().equals(eventName)) {
                events.remove(i);
                return true;
            }
        }

        return false;
    }
}
