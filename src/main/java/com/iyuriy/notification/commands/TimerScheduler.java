package com.iyuriy.notification.commands;

import com.iyuriy.notification.services.EventSchedule;
import com.iyuriy.notification.services.NotificationBotUser;

public interface TimerScheduler {

    /**
     * Creates timer event for user
     * @param schedule - notification schedule
     * @param notificationText - text of the notification
     * @param user - user which requested notification
     */
    void scheduleTimerEvent(EventSchedule schedule, String notificationText, NotificationBotUser user);
}
