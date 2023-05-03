package com.iyuriy.notification.common.parser;

public enum UserEventType {

    ADD("/add"),
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("nocommand"),
    TIME_ZONE("/timezone");

    private final String userEventType;

    UserEventType(String userEventType) {
        this.userEventType = userEventType;
    }

    public String getUserEventType() {
        return userEventType;
    }

}