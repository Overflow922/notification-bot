package com.iyuriy.notification.services;

import com.iyuriy.notification.common.models.ScheduleEvent;

import java.util.List;

public interface EventSender {
    boolean sendEvent(ScheduleEvent event);

    boolean deleteUserEvents(Long id);

    List<String> getUserEvents(Long id);

    List<String> getAllEvents();

}