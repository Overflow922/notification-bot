package com.iyuriy.notification.common.parser;

import com.iyuriy.notification.common.models.ScheduleEvent;

import java.time.ZoneId;

public interface ScheduleParser {

    ScheduleEvent parseEvent(String text, ZoneId userZoneId);
}
