package com.iyuriy.notification.services;

import com.iyuriy.notification.common.parser.UserEvent;

public interface EventSender {
    boolean send(UserEvent event);
}
