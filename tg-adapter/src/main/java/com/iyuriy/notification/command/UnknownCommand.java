package com.iyuriy.notification.command;

import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.UNKNOWN;

public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = """
            Не поддерживаемый тип сообщения \uD83D\uDE1F,
            напиши /help, чтобы узнать поддерживаемые команды.
                    """;

    @Override
    public String commandType() {
        return UNKNOWN.getUserEventType();
    }

    @Override
    public String execute(Update update) {
        return UNKNOWN_MESSAGE;
    }
}