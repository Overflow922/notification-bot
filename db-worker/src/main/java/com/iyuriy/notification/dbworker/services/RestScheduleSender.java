package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.dbworker.configs.RestEventSenderConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestScheduleSender implements ScheduleSenderService{

    private final static RestTemplate restTemplate = new RestTemplate();

    private final RestEventSenderConfiguration configuration;

    @Autowired
    public RestScheduleSender(RestEventSenderConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
        public ResponseEntity<HttpStatus> sendToDbA(ScheduleEvent event) {
            ResponseEntity<HttpStatus> entity = restTemplate.postForEntity(configuration.getUrlDbA(), event, HttpStatus.class);

            entity.getBody();

            return ResponseEntity.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> sendToTgA(ScheduleEvent event) {
        ResponseEntity<HttpStatus> entity = restTemplate.postForEntity(configuration.getUrlTgA(), event, HttpStatus.class);

        entity.getBody();

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
