package com.iyuriy.db_adapter.repositories;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Long> {


}
