package com.iyuriy.notification.common.models;

import com.iyuriy.notification.common.util.ZoneIdConverter;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public final class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = ZoneIdConverter.class)
    @Column(name = "time_zone")
    private ZoneId timeZone;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "chat_id", unique = true)
    private Long chatId;
}