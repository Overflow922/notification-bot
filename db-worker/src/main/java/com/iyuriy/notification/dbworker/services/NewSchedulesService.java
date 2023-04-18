package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.time.Instant;
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

    @Autowired
    public NewSchedulesService(FindNewScheduleService findNewScheduleService, ScheduleSenderService scheduleSenderService) {
        this.findNewScheduleService = findNewScheduleService;
        this.scheduleSenderService = scheduleSenderService;
    }

    @Transactional
    @Scheduled(fixedDelayString = "${interval}")
    public void findNewSchedules() {
        log.info("Scheduler: the time is now {}", dateFormat.format(new Date()));
        List<ScheduleEvent> scheduleEvents = findNewScheduleService.findNewSchedules(Instant.now());
        for (ScheduleEvent event : scheduleEvents) {
//          scheduleSenderService.sendToTgA(event);
            findNewScheduleService.updateWhenSendToAdapter(event);
        }
    }
}
