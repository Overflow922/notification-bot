package com.iyuriy.db_adapter.repositories;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Long> {

    List<ScheduleEvent> findEventsByUserIdAndIsSentToAdapterNull(Long chatId);


    void deleteEventByUserId(Long chatId);
}