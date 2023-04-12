package com.iyuriy.db_adapter.controllers;

import com.iyuriy.db_adapter.services.ScheduleEventService;
import com.iyuriy.db_adapter.util.ScheduleEventNotCreatedException;
import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.common.models.ScheduleEvent;
import org.modelmapper.ModelMapper;
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

    private final ModelMapper modelMapper;

    public ScheduleEventController(ScheduleEventService scheduleEventService, ModelMapper modelMapper) {
        this.scheduleEventService = scheduleEventService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/addEvent")
    public String addEvent(ScheduleEventDto scheduleEventDto){
        return "scheduleEventDto return";
    }

    @GetMapping()
    public List<ScheduleEventDto> getScheduleEvents() {
        return (scheduleEventService.findAll()
                .stream()
                .map(this::convertToScheduleDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ScheduleEventDto getScheduleEvent(@PathVariable("id") long id) {
        return convertToScheduleDTO(scheduleEventService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ScheduleEventDto scheduleEventDto,
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
        scheduleEventService.save(convertToSchedule(scheduleEventDto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private ScheduleEvent convertToSchedule(ScheduleEventDto scheduleEventDto) {
        return modelMapper.map(scheduleEventDto, ScheduleEvent.class);
    }

    private ScheduleEventDto convertToScheduleDTO(ScheduleEvent scheduleEvent){
        return modelMapper.map(scheduleEvent, ScheduleEventDto.class);
    }
}