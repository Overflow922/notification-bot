package com.iyuriy.notification.common.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScheduleParserTest {

    private static final TextScheduleParser PARSER = new TextScheduleParser();
    private static final LocalDate TODAY = LocalDate.now();

    @Test
    void parse_add_1615_text() {
        UserEvent event = PARSER.parseEvent("add 16:15 text");
        assertNotNull(event);

        // check fields
        assertThat(event.getType()).isEqualTo(UserEventType.ADD);
        assertThat(event.getTriggerTime()).isEqualTo(today(16, 15));
        assertThat(event.getNotificationText()).isEqualTo("text");
    }

    private LocalDateTime today(int hour, int mins) {
        return LocalDateTime.of(TODAY, LocalTime.of(hour, mins));
    }

}