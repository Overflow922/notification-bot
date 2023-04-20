package com.iyuriy.notification.controller;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.services.NotificationBotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notify-user")
@AllArgsConstructor
public class NotifyUserController {

    private final NotificationBotService service;

    @PostMapping()
    public ResponseEntity<Void> notifyUser(@RequestBody ScheduleEvent event) {
        ResponseEntity<Void> result;
        try {
            service.notifyUser(event);
            result = ResponseEntity.ok().build();
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return result;
    }
}
