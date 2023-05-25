package com.iyuriy.db_adapter.controllers;

import com.iyuriy.db_adapter.services.ScheduleEventService;
import com.iyuriy.db_adapter.util.ScheduleEventNotFoundException;
import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@RestController
public class ScheduleEventController {

    private final ScheduleEventService scheduleEventService;

    private final ScheduleEventConvertor convertor;

    @PostMapping("/schedule")
    public ResponseEntity<HttpStatus> createScheduleEvent(@RequestBody @Valid ScheduleEventDto scheduleEventDto) {
        ResponseEntity<HttpStatus> result;
        try {
            scheduleEventService.save(convertor.DtoToModel(scheduleEventDto));
            result = ResponseEntity.status(HttpStatus.OK).build();
            log.info("Событие сохранено в базу: {}", scheduleEventDto);
        } catch (ScheduleEventNotFoundException e) {
            result = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            log.info("Ошибка в запросе! {}", e.getMessage());
        } catch (Exception e) {
            result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            log.info("Ошибка сервера! {}", e.getMessage());
        }
        return result;
}

    @GetMapping("/user-events")
    public List<String> getAllUserEvents(@Valid Long id) {

//добавить проверку поля с отправкой
        //сходить в базу посмотреть есть ли айди, если нет, то 400
        //

        if (scheduleEventService.showScheduleEventByUserId(id)==null){

        }

        log.info("Получаем ВСЕ события пользователя с chatId={}", id);
        return (scheduleEventService.showScheduleEventByUserId(id)
                .stream()
                .map(this::convertScheduleToString)
                .collect(Collectors.toList()));
    }

    @PostMapping("/user-delete")
    public ResponseEntity<HttpStatus> deleteUserEvents(@RequestBody @Valid Long chatId) {
        scheduleEventService.deleteScheduleEventByUserId(chatId);
        log.info("Удаляем события пользователя с chatId={}", chatId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private String convertScheduleToString(ScheduleEvent scheduleEvent) {
        return scheduleEvent.getOriginalRq();
    }
}