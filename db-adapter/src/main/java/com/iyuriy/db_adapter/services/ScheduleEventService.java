package com.iyuriy.db_adapter.services;

import com.iyuriy.db_adapter.repositories.ScheduleEventRepository;
import com.iyuriy.db_adapter.util.ScheduleEventDuplicateException;
import com.iyuriy.notification.common.models.ScheduleEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class ScheduleEventService {

    private final ScheduleEventRepository scheduleEventRepository;

    @Transactional
    public void save(ScheduleEvent scheduleEvent) {

        Long chatId = scheduleEvent.getUserId();
        String text = scheduleEvent.getOriginalRq();

        Optional<ScheduleEvent> event = scheduleEventRepository.findEventByUserIdAndOriginalRqAndIsSentToAdapterNull(chatId, text);
        if (event.isPresent()) {
            log.info("Дубликат события! log {}", scheduleEvent);
            throw new ScheduleEventDuplicateException();
        }
        enrichScheduleEvent(scheduleEvent);
        scheduleEventRepository.save(scheduleEvent);
        log.info("Событие сохранено в базу {}", scheduleEvent);
    }

    public List<ScheduleEvent> showScheduleEventByUserId(Long id) {
        log.info("Список событий пользователя с ChatId={} получен", id);
        return scheduleEventRepository.findEventsByUserIdAndIsSentToAdapterNull(id);
    }

    @Transactional
    public void deleteScheduleEventByUserId(Long id) {
        List<ScheduleEvent> events = scheduleEventRepository.findEventsByUserIdAndIsSentToAdapterNull(id);

        for (ScheduleEvent event : events) {
            scheduleEventRepository.deleteEventByUserId(event.getUserId());
        }
        log.info("Все события пользователя ChatId={} удалены", id);
    }

    private void enrichScheduleEvent(ScheduleEvent scheduleEvent) {
        scheduleEvent.setCreatedAt(Instant.now());
    }
}