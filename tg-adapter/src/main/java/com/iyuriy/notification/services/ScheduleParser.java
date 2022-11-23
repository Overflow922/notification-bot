package com.iyuriy.notification.services;

import com.iyuriy.notification.exceptions.NotificationEventException;

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
    public UserEvent parseEvent(String text) {
        return null;
    }
}
