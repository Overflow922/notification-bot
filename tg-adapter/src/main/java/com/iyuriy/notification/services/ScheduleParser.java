package com.iyuriy.notification.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * Parses text of notification schedule and converts it into set of TimerEvents.
 *
 * <p>Rules: message structure: TIME (minutes precision) CONDITION.
 *
 * <p>TIME: local time w/o time zone
 *
 * <p>CONDITION: no condition = today Can begin with [every, on] Ends with day of the week or today.
 *
 * <p>IMPLEMENTATION:
 *
 * <p>Based on chains. Chain element is
 */
public class ScheduleParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("H:m");

    /**
     * Coverts text into set of TimerEvents.
     *
     * @param text - text to be parsed.
     * @return - set of TimerEvents
     */
    public Set<TimerEvent> parse(String text) {
        TimerEventEntry entry = parseText(text);

        LocalTime time = LocalTime.from(FORMATTER.parse(entry.timestamp()));
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.now(), time);

        if (entry.condition() == null) {
            return Set.of(new TimerEvent(dateTime));
        }

        Duration duration;
        if ("every day".equals(entry.condition())) {
            duration = Duration.ofDays(1);
        } else {
            throw new IllegalArgumentException();
        }
        TimerEvent event = new TimerEvent(true, dateTime, duration);

        return Set.of(event);
    }

    private TimerEventEntry parseText(String text) {
        text = text.trim();
        int firstSpace = text.indexOf(" ");
        if (firstSpace == -1) return new TimerEventEntry(text, null);

        return new TimerEventEntry(
                text.substring(0, firstSpace).trim(), text.substring(firstSpace).trim());
    }
}
