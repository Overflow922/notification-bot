package com.iyuriy.notification.controller;

import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.services.NotificationBotService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/notify-user")
@AllArgsConstructor
public class NotifyUserController {

    private final NotificationBotService service;

    @PostMapping()
    public ResponseEntity<Void> notifyUser(@RequestBody ScheduleEventDto event) {
        ResponseEntity<Void> result;
        try {
            service.notifyUser(event.getNotificationText(), event.getUserId());
            result = ResponseEntity.ok().build();
            log.info("Событие успешно отправлено пользователю: {}", event);
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.info("Ошибка! {}", e.getMessage());
        }
        return result;
    }
}
