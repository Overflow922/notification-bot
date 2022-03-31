package com.iyuriy.notification.services;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ScheduleParserTest {

    private static final ScheduleParser PARSER = new ScheduleParser();
    private static final LocalDate TODAY = LocalDate.now();

    public static Stream<Arguments> timestampProvider() {
        return Stream.of(
                Arguments.of(
                        "16:30 every day",
                        Set.of(new TimerEvent(true, TODAY.atTime(16, 30), Duration.ofDays(1)))),
                Arguments.of(
                        "9:30",
                        Set.of(new TimerEvent(false, TODAY.atTime(9, 30), TimerEvent.INFINITY))),
                Arguments.of(
                        "09:30",
                        Set.of(new TimerEvent(false, TODAY.atTime(9, 30), TimerEvent.INFINITY)))
                );
    }

    @ParameterizedTest
    @MethodSource("timestampProvider")
    void test(String input, Set<TimerEvent> expected) {
        Set<TimerEvent> timerEvents = PARSER.parse(input);

        assertThat(timerEvents).isEqualTo(expected);
    }
}
