package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class NewSchedulesService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(FindNewScheduleServiceImpl.class);

    private final FindNewScheduleService findNewScheduleService;

    @Autowired
    public NewSchedulesService(FindNewScheduleService findNewScheduleService) {
        this.findNewScheduleService = findNewScheduleService;
    }

    @Scheduled(fixedRate = 60_000)
    public void findNewSchedules() {

        log.info("Scheduler: the time is now {}", dateFormat.format(new Date()));
        List<ScheduleEvent> scheduleEvents = findNewScheduleService.findNewSchedules(Instant.now());
        for (ScheduleEvent s :scheduleEvents) {
            findNewScheduleService.updateWhenSendToAdapter(s);
        }

    }
}
