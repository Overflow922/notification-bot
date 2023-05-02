package com.iyuriy.notification.common.parser;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.models.User;

import java.time.*;
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
    public ScheduleEvent parseEvent(String text, ZoneId userZoneId) {
        try {
            String[] strings = text.split(" ", 3);
            String time = strings[1];
            LocalDateTime timeToTrigger = parseTime(time);
            return ScheduleEvent.builder()
                    .notificationText(strings[2])
                    .timeToTrigger(ZonedDateTime.of(timeToTrigger, userZoneId).toInstant())
                    .originalRq(text)
                    .build();
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
