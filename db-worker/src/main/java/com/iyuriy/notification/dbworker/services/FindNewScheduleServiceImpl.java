package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.text.SimpleDateFormat;

@EnableScheduling
@Service
public class FindNewScheduleServiceImpl implements FindNewScheduleService {

    private final ScheduleEvent scheduleEvent;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(FindNewScheduleServiceImpl.class);

    @Autowired
    public FindNewScheduleServiceImpl(ScheduleEvent scheduleEvent) {
        this.scheduleEvent = scheduleEvent;
    }
    @Scheduled(fixedRate = 60*1000)
    @Override
    public void findNewSchedules() {
        log.info("Scheduler: the time is now {}", dateFormat.format(new Date()));

    }

}