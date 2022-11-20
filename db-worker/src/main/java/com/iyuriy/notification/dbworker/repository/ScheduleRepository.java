package com.iyuriy.notification.dbworker.repository;

import com.iyuriy.notification.dbworker.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
}
