package com.iyuriy.notification.common.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Entity
@Table(name = "Users")
public final class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_zone")
    private Instant timeZone;

    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "user")
    private List<ScheduleEvent> users;
}