package com.iyuriy.notification.services;

import com.iyuriy.notification.common.dto.CommandDto;
import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.configs.RestEventSenderConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
    public HttpStatus sendEvent(ScheduleEventDto event) {
        try {
            ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getUrlSchedule(), event, Void.class);
            return entity.getStatusCode();
        } catch (HttpClientErrorException.Conflict e) {
            log.error("Ошибка в sendEvent");
            return HttpStatus.CONFLICT;
        }
    }

    @Override
    public HttpStatus deleteUserEvents(Long chatId) {
        ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getUrlUserDelete(), chatId, Void.class);
        return entity.getStatusCode();
    }

    @Override
    public List<String> getUserEvents(Long id) {
        ResponseEntity<String[]> entity = restTemplate.getForEntity(configuration.getUrlUserEvents() + "?id={id}", String[].class, id);

        if (entity.getStatusCode() == HttpStatus.OK && entity.getBody() != null) {
            return Arrays.asList(entity.getBody());
        }
        throw new RuntimeException();
    }

    @Override
    public HttpStatus deleteOneUserEvent(Long chatId, String text) {
        try {
            CommandDto commandDto = CommandDto.builder().chatId(chatId).text(text).build();
            ResponseEntity<Void> entity = restTemplate.postForEntity(configuration.getUrlDeleteOneEvent(), commandDto, Void.class);
            return entity.getStatusCode();
        } catch (HttpClientErrorException.BadRequest e) {
            log.error("Ошибка в deleteOneUserEvent sender");
            return HttpStatus.BAD_REQUEST;
        }
    }

}