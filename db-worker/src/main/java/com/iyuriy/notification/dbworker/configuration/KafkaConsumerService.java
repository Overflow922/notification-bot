package com.iyuriy.notification.dbworker.configuration;

import com.iyuriy.notification.common.entities.AddNotification;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "notification-add", groupId = "foo")
    public void addNotificationEvent(@Payload ConsumerRecord<String, AddNotification> message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
