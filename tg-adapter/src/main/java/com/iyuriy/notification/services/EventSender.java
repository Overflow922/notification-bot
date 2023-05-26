package com.iyuriy.notification.services;

import com.iyuriy.notification.common.dto.ScheduleEventDto;

import java.util.List;

import java.util.List;

public interface EventSender {

    boolean sendEvent(ScheduleEventDto event);

    boolean deleteUserEvents(Long id);

    List<String> getUserEvents(Long id);
}