package com.iyuriy.notification.common.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TextScheduleParser implements ScheduleParser {

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
        try {
            String[] strings = text.split(" ", 3);
            String command = strings[0];
            UserEventType type = parseCommand(command);
            String time = strings[1];
            LocalDateTime timeToTrigger = parseTime(time);
            return new UserEvent(type, timeToTrigger, strings[2]);
        } catch (RuntimeException e) {
            throw new NotificationEventException(e);
        }
    }

    private LocalDateTime parseTime(String timeStr) {
        LocalTime time = LocalTime.parse(timeStr, FORMATTER);
        return LocalDateTime.of(LocalDate.now(), time);
    }

    private UserEventType parseCommand(String command) {
        return UserEventType.valueOf(command.toUpperCase());
    }
}
