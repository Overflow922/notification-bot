package com.iyuriy.notification.command;

import org.springframework.stereotype.Service;

import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.*;


@Service
public class HelpCommand implements Command {

    public static final String HELP_MESSAGE = String.format(
            "Доступные команды:\n\n" +
                    "%s - Старт\n\n" +
                    "%s - Получить помощь\n\n" +
                    "%s - Добавить событие\n\n" +
                    "%s - Удалить событие\n\n" +
                    "%s - Посмотреть все события\n\n" +
                    "%s - Узнать текущую временную зону\n\n" +
                    "%s - Приостановить работу\n\n",
            START.getUserEventType(),
            HELP.getUserEventType(),
            ADD.getUserEventType(),
            DELETE_EVENT.getUserEventType(),
            ALL_USER_EVENTS.getUserEventType(),
            TIME_ZONE.getUserEventType(),
            STOP.getUserEventType());

    @Override
    public String commandType() {
        return HELP.getUserEventType();
    }

    @Override
    public String execute(Update update) {
        return HELP_MESSAGE;
    }
}