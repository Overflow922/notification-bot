package com.iyuriy.notification.dbworker.repositories;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Long> {

    List<ScheduleEvent> findByTimeToTriggerBeforeAndIsSentToAdapterIsNull(Instant timeToTrigger);

}