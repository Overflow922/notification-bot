package com.iyuriy.notification.dbworker.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

public interface FindNewScheduleService {

    List<ScheduleEvent> findNewSchedules(Instant timeToTrigger);

    void updateWhenSendToAdapter(ScheduleEvent updatedScheduleEvent);

}
