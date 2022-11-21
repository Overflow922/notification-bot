package com.iyuriy.notification.dbworker.service;

import com.iyuriy.notification.common.entities.AddNotification;
import com.iyuriy.notification.dbworker.model.Schedule;
import com.iyuriy.notification.dbworker.model.Task;
import com.iyuriy.notification.dbworker.repository.ScheduleRepository;
import com.iyuriy.notification.dbworker.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaConsumerService {

    private final ScheduleRepository scheduleRepository;
    private final TaskRepository taskRepository;

    @KafkaListener(topics = "notification-add", groupId = "foo")
    public void addNotificationEvent(@Payload ConsumerRecord<String, AddNotification> message) {
        AddNotification value = message.value();
        Task task = createTask(value);
        taskRepository.save(task);
        Schedule schedule = createSchedule(value, task);
        scheduleRepository.save(schedule);
    }

    private Schedule createSchedule(AddNotification value, Task task) {
        return Schedule.builder()
                .timestamp(value.getTimeToTrigger())
                .task(task)
                .build();
    }

    private Task createTask(AddNotification value) {
        return Task.builder()
                .text(value.getNotificationText())
                .originalRequest(value.getOriginalRq())
                .build();
    }
}
