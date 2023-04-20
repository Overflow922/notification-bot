package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.dbworker.configs.RestEventSenderConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RestScheduleSender implements ScheduleSenderService {

    private final static RestTemplate restTemplate = new RestTemplate();

    private final RestEventSenderConfiguration configuration;

    @Autowired
    public RestScheduleSender(RestEventSenderConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public ResponseEntity<HttpStatus> sendToDbA(ScheduleEvent event) {
        try {
            ResponseEntity<HttpStatus> entity = restTemplate.postForEntity(configuration.getUrlDbA(), event, HttpStatus.class);
            if (entity.getStatusCode() != HttpStatus.OK) {
                return entity;
            }
            log.info("{} event успешно отправлен в db-adapter!", event);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            log.error("ОШИБКА! {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<HttpStatus> sendToTgA(ScheduleEvent event) {
        try {
            ResponseEntity<HttpStatus> entity = restTemplate.postForEntity(configuration.getUrlTgA(), event, HttpStatus.class);
            if (entity.getStatusCode() != HttpStatus.OK) {
                return entity;
            }
            log.info("{} event успешно отправлен в tg-adapter!", event);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            log.error("ОШИБКА! {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}