package com.iyuriy.notification.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class TimerEvent {

    private final boolean isRepeatable;
    private final LocalDateTime triggerTime;
    private final Duration interval;

    public TimerEvent(boolean isRepeatable, LocalDateTime triggerTime, Duration interval) {
        this.isRepeatable = isRepeatable;
        this.triggerTime = triggerTime;
        this.interval = interval;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }

    public Duration getInterval() {
        return interval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimerEvent that = (TimerEvent) o;
        return isRepeatable == that.isRepeatable && Objects.equals(triggerTime, that.triggerTime) && Objects.equals(interval, that.interval);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isRepeatable, triggerTime, interval);
    }
}
