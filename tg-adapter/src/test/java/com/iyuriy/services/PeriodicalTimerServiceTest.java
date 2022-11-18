package com.iyuriy.services;

import com.iyuriy.notification.services.EventSchedule;
import com.iyuriy.notification.services.PeriodicalTimerService;
import com.iyuriy.notification.services.TimerEvent;
import com.iyuriy.notification.services.TimerService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

class PeriodicalTimerServiceTest {

    private static final int DELAY = 50;
    private static final boolean REPEATABLE = true;
    private static final boolean NON_REPEATABLE = false;
    private final TimerService service = PeriodicalTimerService.of();
    private final AtomicInteger counter = new AtomicInteger(0);

    public static Stream<Arguments> timerEventGenerator() {
        return Stream.of(
                Arguments.arguments(NON_REPEATABLE, DELAY, TimerEvent.INFINITY, 1),
                Arguments.arguments(REPEATABLE, DELAY, Duration.ofMillis(DELAY), 3));
    }

    void callback() {
        counter.incrementAndGet();
    }

    @ParameterizedTest()
    @MethodSource("timerEventGenerator")
    void whenTimerEventsAreScheduled_thenShouldInvokeCallback(
            boolean isRepeatable, long delay, Duration interval, long expectedCallNumber)
            throws InterruptedException {
        counter.set(0);
        LocalDateTime triggerTime = LocalDateTime.now().plus(Duration.ofMillis(delay));
        final EventSchedule schedule =
                new EventSchedule.EventScheduleBuilder()
                        .add(new TimerEvent(isRepeatable, triggerTime, interval))
                        .build();
        service.schedule(schedule, timerEvent -> callback());
        Thread.sleep(expectedCallNumber * DELAY);

        AssertionsForClassTypes.assertThat(counter.get()).isEqualTo(expectedCallNumber);
    }
}
