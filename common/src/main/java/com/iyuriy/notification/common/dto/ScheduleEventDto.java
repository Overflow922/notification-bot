package com.iyuriy.notification.common.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Setter
@Getter
@ToString
public final class ScheduleEventDto {

    @NotEmpty(message = "timeToTrigger should not be empty")
    private Instant timeToTrigger;

    private String notificationText;

    private String originalRq;

    @NotEmpty(message = "userId should not be empty")
    private Long userId;
}