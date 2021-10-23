package com.iyuriy.notification.services;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScheduleParserTest {

    private final static ScheduleParser PARSER = new ScheduleParser();

    @Test
    void test() {
        LocalTime time = LocalTime.of(16, 30);
        LocalDate today = LocalDate.now();
        Set<TimerEvent> expected = Set.of(new TimerEvent(true, LocalDateTime.of(today, time), Duration.of(24, ChronoUnit.HOURS)));
        String text = "16:30 every day";

        Set<TimerEvent> timerEvents = PARSER.parse(text);

        assertThat(timerEvents).isEqualTo(expected);

    }

}