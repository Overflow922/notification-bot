package com.iyuriy.db_adapter.services;

import com.iyuriy.db_adapter.repositories.ScheduleEventRepository;
import com.iyuriy.notification.common.models.ScheduleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Slf4j
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
        log.info("Событие сохранено в базу {}", scheduleEvent);
    }

    public List<ScheduleEvent> showScheduleEventByUserId(Long id) {
        log.info("Список событий пользователя с ChatId={} получен", id);
        return scheduleEventRepository.findEventsByUserId(id);
    }

    @Transactional
    public void deleteScheduleEventByUserId(Long id) {
        List<ScheduleEvent> events = scheduleEventRepository.findEventsByUserId(id);
        for (ScheduleEvent event : events) {
            scheduleEventRepository.deleteEventByUserId(event.getUserId());
        }
        log.info("Все события пользователя ChatId={} удалены", id);
    }

    private void enrichScheduleEvent(ScheduleEvent scheduleEvent) {
        scheduleEvent.setCreatedAt(Instant.now());
    }
}