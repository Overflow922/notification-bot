package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.dbworker.repositories.ScheduleEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FindNewScheduleServiceImpl implements FindNewScheduleService {

    private final ScheduleEventRepository scheduleEventRepository;

    private final ScheduleEvent scheduleEvent;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final Logger log = LoggerFactory.getLogger(FindNewScheduleServiceImpl.class);

    @Autowired
    public FindNewScheduleServiceImpl(ScheduleEventRepository scheduleEventRepository, ScheduleEvent scheduleEvent) {
        this.scheduleEventRepository = scheduleEventRepository;
        this.scheduleEvent = scheduleEvent;
    }

    @Scheduled(fixedRate = 60_000)
    @Override
    public void findNewSchedules() {

        log.info("Scheduler: the time is now {}", dateFormat.format(new Date()));
    }

    public List<ScheduleEvent> findNewSchedules(Instant timeToTrigger, Instant isSentToAdapter) {
        return scheduleEventRepository.findByTimeToTriggerBeforeAndIsSentToAdapterIsNull(timeToTrigger, isSentToAdapter);
    }

    @Transactional
    public void updateWhenSendToAdapter(ScheduleEvent updatedScheduleEvent) {
        updatedScheduleEvent.setIsSentToAdapter(Instant.now());
        scheduleEventRepository.save(updatedScheduleEvent);
    }
}