package com.iyuriy.db_adapter.controllers;

import com.iyuriy.db_adapter.services.ScheduleEventService;
import com.iyuriy.db_adapter.util.ScheduleEventNotCreatedException;
import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/schedule")
@RestController
public class ScheduleEventController {

    private final ScheduleEventService scheduleEventService;

    private final ScheduleEventConvertor convertor;

    @Autowired
    public ScheduleEventController(ScheduleEventService scheduleEventService, ScheduleEventConvertor convertor) {
        this.scheduleEventService = scheduleEventService;
        this.convertor = convertor;
    }

    @PostMapping
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
            throw new ScheduleEventNotCreatedException(errorMsg.toString());
        }
        scheduleEventService.save(convertor.DtoToModel(scheduleEventDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<ScheduleEventDto> getAllScheduleEvents() {
        return (scheduleEventService.findAll()
                .stream()
                .map(convertor::ModelToDto)
                .collect(Collectors.toList()));
    }
}