package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.common.models.ScheduleEvent;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@Transactional(readOnly = true)
public class NewSchedulesService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    private final FindNewScheduleService findNewScheduleService;

    private final ScheduleSenderService scheduleSenderService;

    private final ModelMapper modelMapper;

    @Autowired
    public NewSchedulesService(FindNewScheduleService findNewScheduleService, ScheduleSenderService scheduleSenderService, ModelMapper modelMapper) {
        this.findNewScheduleService = findNewScheduleService;
        this.scheduleSenderService = scheduleSenderService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Scheduled(fixedDelayString = "${interval}")
    public void findNewSchedules() {
        log.info("Scheduler: the time is now {}", dateFormat.format(new Date()));
        List<ScheduleEvent> scheduleEvents = findNewScheduleService.findNewSchedules(Instant.from(ZonedDateTime.now()));
        for (ScheduleEvent event : scheduleEvents) {
            ResponseEntity<HttpStatus> response = scheduleSenderService.sendToTgA(convertToScheduleDTO(event));
            if (response.getStatusCode() == HttpStatus.OK){
                findNewScheduleService.updateWhenSendToAdapter(event);
            }
        }
    }

    private ScheduleEventDto convertToScheduleDTO(ScheduleEvent scheduleEvent) {
        return modelMapper.map(scheduleEvent, ScheduleEventDto.class);
    }
}
