package com.iyuriy.notification.common.parser;

import com.iyuriy.notification.common.models.ScheduleEvent;

public interface ScheduleParser {

    ScheduleEvent parseEvent(String text);
}
