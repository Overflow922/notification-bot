package com.iyuriy.notification.common.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
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

}