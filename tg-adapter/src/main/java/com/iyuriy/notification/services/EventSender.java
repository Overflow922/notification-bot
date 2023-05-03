package com.iyuriy.notification.services;

import com.iyuriy.notification.common.models.ScheduleEvent;

public interface EventSender {
    boolean send(ScheduleEvent event, String command);
}
