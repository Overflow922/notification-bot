package com.iyuriy.db_adapter.controllers;

import com.iyuriy.db_adapter.services.ScheduleEventService;
import com.iyuriy.db_adapter.util.ScheduleEventNotCreatedException;
import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public ResponseEntity<HttpStatus> createScheduleEvent(@RequestBody @Valid ScheduleEventDto scheduleEventDto,
                                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" ~ ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            log.error("Ошибка {}", errorMsg);
            throw new ScheduleEventNotCreatedException(errorMsg.toString());
        }
        scheduleEventService.save(convertor.DtoToModel(scheduleEventDto));
        log.info("Save to database event: {}", scheduleEventDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/user-events")
    public List<ScheduleEventDto> getAllUserEvents(Long id) {
        log.info("Получаем ВСЕ события пользователя с id={}", id);
        return (scheduleEventService.showScheduleEventByUserId(id)
                .stream()
                .map(convertor::ModelToDto)
                .collect(Collectors.toList()));
    }

    @PostMapping("/user-delete")
    public ResponseEntity<HttpStatus> deleteUserEvents(Long id) {
        scheduleEventService.deleteScheduleEventByUserId(id);
        log.info("Удаляем события пользователя с id={}", id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/all-events")
    public List<ScheduleEventDto> getAllEvents() {
        log.info("Получаем ВСЕ события из базы");
        return scheduleEventService.findAll()
                .stream()
                .map(convertor::ModelToDto)
                .collect(Collectors.toList());
    }

}