package com.iyuriy.db_adapter.services;

import com.iyuriy.db_adapter.repositories.ScheduleEventRepository;
import com.iyuriy.db_adapter.util.ScheduleEventErrorResponse;
import com.iyuriy.db_adapter.util.ScheduleEventNotCreatedException;
import com.iyuriy.db_adapter.util.ScheduleEventNotFoundException;
import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

    public ScheduleEvent findOne(Long id) {
        Optional<ScheduleEvent> foundScheduleEvent = scheduleEventRepository.findById(id);
        return foundScheduleEvent.orElseThrow(ScheduleEventNotFoundException::new);
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

    private ResponseEntity<ScheduleEventErrorResponse> handleException(ScheduleEventNotFoundException e) {
        ScheduleEventErrorResponse response =
                new ScheduleEventErrorResponse("Schedule Event with this id not found",
                        System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ScheduleEventErrorResponse> handleException(ScheduleEventNotCreatedException e) {
        ScheduleEventErrorResponse response =
                new ScheduleEventErrorResponse(
                        e.getMessage(),
                        System.currentTimeMillis()
                );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}