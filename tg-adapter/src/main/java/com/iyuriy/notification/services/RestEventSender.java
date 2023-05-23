package com.iyuriy.notification.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.configs.RestEventSenderConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RestEventSender implements EventSender {

    private final static RestTemplate restTemplate = new RestTemplate();
    private final RestEventSenderConfiguration configuration;

    @Override
    public boolean sendEvent(ScheduleEvent event) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getUrlSchedule(), event, Void.class);

        return entity.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public boolean deleteUserEvents(Long chatId) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getUrlUserDelete(), chatId, Void.class);

        return entity.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public List<String> getUserEvents(Long id) {
        ResponseEntity<String[]> entity = restTemplate.getForEntity(configuration.getUrlUserEvents() + "?id={id}", String[].class, id);

        if (entity.getStatusCode() == HttpStatus.OK && entity.getBody() != null) {
            return Arrays.asList(entity.getBody());
        }
        throw new RuntimeException();
    }

}