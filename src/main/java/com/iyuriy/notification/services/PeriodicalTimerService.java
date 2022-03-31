package com.iyuriy.notification.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/** Periodically checks which actions can be executed. */
public final class PeriodicalTimerService implements TimerService {

    ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

    private PeriodicalTimerService() {}

    public static TimerService of() {
        return new PeriodicalTimerService();
    }

    @Override
    public void schedule(EventSchedule schedule, Consumer<TimerEvent> action) {
        for (var event : schedule.getEvents()) {
            Duration delta = calculateInvocationDuration(event);
            scheduleEvent(delta, event, action);
        }
    }

    private Duration calculateInvocationDuration(TimerEvent event) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(event.getTriggerTime()))
            throw new IllegalArgumentException(
                    "trigger time can't be in past. now: "
                            + now
                            + ", trigger time: "
                            + event.getTriggerTime());

        return Duration.between(event.getTriggerTime(), now);
    }

    private void scheduleEvent(Duration duration, TimerEvent event, Consumer<TimerEvent> action) {
        service.schedule(
                () -> {
                    action.accept(event);
                    if (event.isRepeatable()) {
                        scheduleEvent(event.getInterval(), event, action);
                    }
                },
                duration.toMillis(),
                TimeUnit.MILLISECONDS);
    }
}
