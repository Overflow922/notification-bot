package com.iyuriy.notification.common.parser;

public interface ScheduleParser {

    // TODO use model type from https://github.com/Overflow922/notification-bot/pull/12
    UserEvent parseEvent(String text);
}
