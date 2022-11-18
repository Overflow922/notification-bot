package com.iyuriy.notification.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EventSchedule {

    private final Set<TimerEvent> events;

    private EventSchedule(Set<TimerEvent> events) {
        this.events = events;
    }

    public Set<TimerEvent> getEvents() {
        return Collections.unmodifiableSet(events);
    }

    public EventScheduleBuilder of() {
        return new EventScheduleBuilder();
    }

    public static class EventScheduleBuilder {

        private final Set<TimerEvent> events = new HashSet<>();

        public EventScheduleBuilder add(TimerEvent timerEvent) {
            events.add(timerEvent);
            return this;
        }

        public EventSchedule build() {
            return new EventSchedule(events);
        }
    }
}
