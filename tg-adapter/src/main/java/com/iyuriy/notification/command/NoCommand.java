package com.iyuriy.notification.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {


    public static final String NO_MESSAGE =
            "Поддерживаются команды,\n"
                    + "начинающиеся со слэша(/).\n"
                    + "Чтобы посмотреть список\n"
                    + "доступных команд введите /help";

    @Override
    public String execute(Update update) {
        return NO_MESSAGE;
    }
}