package com.iyuriy.notification.services;

import com.iyuriy.notification.common.dto.ScheduleEventDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface EventSender {
    HttpStatus sendEvent(ScheduleEventDto event);

    HttpStatus deleteUserEvents(Long id);

    List<String> getUserEvents(Long id);

    HttpStatus deleteOneUserEvent(Long id, String text);

}