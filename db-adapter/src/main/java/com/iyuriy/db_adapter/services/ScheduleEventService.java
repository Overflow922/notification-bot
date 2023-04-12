package com.iyuriy.db_adapter.services;

import com.iyuriy.db_adapter.repositories.ScheduleEventRepository;
import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ScheduleEventService {

    private final ScheduleEventRepository scheduleEventRepository;

    @Autowired
    public ScheduleEventService(ScheduleEventRepository scheduleEventRepository) {
        this.scheduleEventRepository = scheduleEventRepository;
    }

    public List<ScheduleEvent> findAll() {
        return scheduleEventRepository.findAll();
    }


    @Transactional
    public void save(ScheduleEvent scheduleEvent) {
        enrichScheduleEvent(scheduleEvent);
        scheduleEventRepository.save(scheduleEvent);
    }

    @Transactional
    public void delete(Long id) {
        scheduleEventRepository.deleteById(id);
    }

    private void enrichScheduleEvent(ScheduleEvent scheduleEvent) {
        scheduleEvent.setCreatedAt(Instant.now());
    }
}