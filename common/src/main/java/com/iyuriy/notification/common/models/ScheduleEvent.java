package com.iyuriy.notification.common.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Entity
@Table(name = "schedule_event")
public final class ScheduleEvent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "timeToTrigger should not be empty or null")
    @Column(name = "time_to_trigger")
    private Instant timeToTrigger;

    @Column(name = "notification_text")
    private String notificationText;

    @Column(name = "original_rq")
    private String originalRq;

    @Column(name = "is_sent_to_adapter")
    private Instant isSentToAdapter;

    @Column(name = "created_at")
    private Instant createdAt;

    @NotEmpty(message = "userId should not be empty or null")
    @Column(name = "user_id")
    private Long userId;
}