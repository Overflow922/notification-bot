package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.dto.ScheduleEventDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ScheduleSenderService {

    ResponseEntity<HttpStatus> sendToDbA(ScheduleEventDto event);

    ResponseEntity<HttpStatus> sendToTgA(ScheduleEventDto event);
}