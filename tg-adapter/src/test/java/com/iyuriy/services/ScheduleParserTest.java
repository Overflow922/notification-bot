package com.iyuriy.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.parser.TextScheduleParser;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

import static java.time.ZoneId.systemDefault;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScheduleParserTest {
    private static final TextScheduleParser PARSER = new TextScheduleParser();
    private static final LocalDate TODAY = LocalDate.now();

    @Test
    void parse_add_1615_text() {
        ScheduleEvent event = PARSER.parseEvent("add 16:15 text");
        assertNotNull(event);

        // check fields
        assertThat(event.getTimeToTrigger()).isEqualTo(today(16, 15));
        assertThat(event.getNotificationText()).isEqualTo("text");
    }

    private Instant today(int hour, int mins) {
        return ZonedDateTime.of(TODAY, LocalTime.of(hour, mins), systemDefault()).toInstant();
    }
}
