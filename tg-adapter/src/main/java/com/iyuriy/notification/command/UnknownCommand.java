package com.iyuriy.notification.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE =
            "Не поддерживаемый тип сообщения \uD83D\uDE1F, \n" +
                    "напиши /help, чтобы узнать поддерживаемые команды.";


    @Override
    public String execute(Update update) {
        return UNKNOWN_MESSAGE;
    }
}
