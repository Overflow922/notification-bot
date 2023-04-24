package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;

import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
public class NewSchedulesService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    private final FindNewScheduleService findNewScheduleService;

    private final ScheduleSenderService scheduleSenderService;

    private final ScheduleEventConvertor convertor;

    @Transactional
    @Scheduled(fixedDelayString = "${interval}")
    public void findNewSchedules() {
        List<ScheduleEvent> scheduleEvents = findNewScheduleService.findNewSchedules(Instant.from(ZonedDateTime.now()));
        if (!scheduleEvents.isEmpty()) {
            log.info("Scheduler: the time is now {}", dateFormat.format(new Date()));
        }
        for (ScheduleEvent event : scheduleEvents) {
            ResponseEntity<HttpStatus> response = scheduleSenderService.sendToTgA(convertor.ModelToDto(event));
            if (response.getStatusCode() == HttpStatus.OK) {
                findNewScheduleService.updateWhenSendToAdapter(event);
            }
        }
    }
}