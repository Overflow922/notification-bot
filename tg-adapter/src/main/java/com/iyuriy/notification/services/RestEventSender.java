package com.iyuriy.notification.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.configs.RestEventSenderConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class RestEventSender implements EventSender {

    private final static RestTemplate restTemplate = new RestTemplate();
    private final RestEventSenderConfiguration configuration;

    @Override
    public boolean send(ScheduleEvent event, String command ) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getUrl(), event, Void.class);

        return entity.getStatusCode() == HttpStatus.OK;
    }
}
