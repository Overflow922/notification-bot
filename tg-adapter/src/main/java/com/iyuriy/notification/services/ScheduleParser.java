package com.iyuriy.notification.services;

import com.iyuriy.notification.exceptions.NotificationEventException;
import com.iyuriy.notification.model.UserEventType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduleParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("H:m");

    /**
     * Parses text from client.
     * The format is:
     * COMMAND TIME TEXT
     * Returns NotificationEvent as a result
     * @param text - text to parse
     * @throws NotificationEventException if failed to parse
     * @return -
     */
    // add 16:45 call boss
    public UserEvent parseEvent(String text) {
        String[] strings = text.split(" ", 3);
        String command = strings[0];
        UserEventType type = parseCommand(command);
        String time = strings[1];
        LocalDateTime timeToTrigger = parseTime(time);
        return new UserEvent(type, timeToTrigger, strings[2]);
    }

    private LocalDateTime parseTime(String time) {
        return null;
    }

    private UserEventType parseCommand(String command) {
        return null;
    }
}
