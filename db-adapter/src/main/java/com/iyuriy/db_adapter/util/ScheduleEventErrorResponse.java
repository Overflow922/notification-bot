package com.iyuriy.db_adapter.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleEventErrorResponse {

    private String message;
    private Long timestamp;

    public ScheduleEventErrorResponse(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}