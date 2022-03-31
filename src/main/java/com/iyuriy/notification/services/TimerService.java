package com.iyuriy.notification.services;

import java.util.function.Consumer;

public interface TimerService {

    /**
     * Creates timer event
     * @param schedule - notification schedule
     * @param action - action to be triggered at {@code schedule}
     */
    void schedule(EventSchedule schedule, Consumer<TimerEvent> action);
}
