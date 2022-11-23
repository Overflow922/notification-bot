package com.iyuriy.notification.services;

import com.iyuriy.notification.model.UserEventType;

import java.time.Instant;

/**
 * Represents notification event.
 * Event can have different type:
 * 1. Add
 * Trigger time - time when user should be notified
 * Notification text - text which will be sent to user
 *
 * This class is main abstraction of user activity.
 */
public class UserEvent {
    private final UserEventType type;
    private final Instant triggerTime;
    private final String notificationText;

    public UserEvent(UserEventType type, Instant triggerTime, String notificationText) {
        this.type = type;
        this.triggerTime = triggerTime;
        this.notificationText = notificationText;
    }

    public UserEventType getType() {
        return type;
    }

    public Instant getTriggerTime() {
        return triggerTime;
    }

    public String getNotificationText() {
        return notificationText;
    }
}
