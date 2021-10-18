package com.iyuriy.notification.services;

import java.util.Set;

/**
 * Parses text of notification schedule and converts it into set of TimerEvents.
 *
 * Rules:
 * message structure:
 * TIME (minutes precision) CONDITION.
 *
 * TIME:
 * local time w/o time zone
 *
 * CONDITION:
 * no condition = today
 * Can begin with [every, on]
 * Ends with day of the week or today.
 *
 */
public class ScheduleParser {

    /**
     * Coverts text into set of TimerEvents.
     * @param text - text to be parsed.
     * @return - set of TimerEvents
     */
    public Set<TimerEvent> parse(String text) {
        return null;
    }
}
