package com.iyuriy.notification.common.parser;

import java.time.LocalDateTime;

/**
 * Represents notification event.
 * Event can have different type:
 * 1. Add
 * Trigger time - time when user should be notified
 * Notification text - text which will be sent to user
 * This class is main abstraction of user activity.
 */
public class UserEvent {
    private final UserEventType type;
    private final LocalDateTime triggerTime;
    private final String notificationText;

    public UserEvent(UserEventType type, LocalDateTime triggerTime, String notificationText) {
        this.type = type;
        this.triggerTime = triggerTime;
        this.notificationText = notificationText;
    }

    public UserEventType getType() {
        return type;
    }

    public LocalDateTime getTriggerTime() {
        return triggerTime;
    }

    public String getNotificationText() {
        return notificationText;
    }
}
