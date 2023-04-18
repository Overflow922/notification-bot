package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ScheduleSenderService {

    ResponseEntity<HttpStatus> sendToDbA(ScheduleEvent event);

    ResponseEntity<HttpStatus> sendToTgA(ScheduleEvent event);
}