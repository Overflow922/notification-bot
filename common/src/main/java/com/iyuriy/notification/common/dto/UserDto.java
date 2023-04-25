package com.iyuriy.notification.common.dto;

import com.iyuriy.notification.common.models.ScheduleEvent;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@ToString
public final class UserDto {

    private Instant timeZone;
    private List<ScheduleEvent> users;
}