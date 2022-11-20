package com.iyuriy.notification.dbworker.repository;

import com.iyuriy.notification.dbworker.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
