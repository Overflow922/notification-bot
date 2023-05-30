package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.dbworker.repositories.ScheduleEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class FindNewScheduleServiceImpl implements FindNewScheduleService {

    private final ScheduleEventRepository scheduleEventRepository;

    public List<ScheduleEvent> findNewSchedules(Instant timeToTrigger) {
        return scheduleEventRepository.findByTimeToTriggerBeforeAndIsSentToAdapterIsNull(timeToTrigger);
    }

    @Transactional
    public void updateWhenSendToAdapter(ScheduleEvent updatedScheduleEvent) {
        updatedScheduleEvent.setIsSentToAdapter(Instant.now());
        scheduleEventRepository.save(updatedScheduleEvent);
    }
}