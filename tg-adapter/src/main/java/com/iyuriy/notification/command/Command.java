package com.iyuriy.notification.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    String execute(Update update);
}