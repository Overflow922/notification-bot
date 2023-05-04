package com.iyuriy.notification.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    String commandType();

    String execute(Update update);
}