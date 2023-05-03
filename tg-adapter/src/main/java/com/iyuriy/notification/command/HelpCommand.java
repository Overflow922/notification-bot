package com.iyuriy.notification.command;

import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.*;

public class HelpCommand implements Command {

    public static final String HELP_MESSAGE = String.format(
            "Доступные команды:\n\n" +
                    "%s - получить помощь\n\n" +
                    "%s - Добавить задание\n\n" +
                    "%s - Ввести Time Zone\n\n" +
                    "%s - приостановить работу\n\n",
            HELP.getUserEventType(),
            ADD.getUserEventType(),
            TIME_ZONE.getUserEventType(),
            STOP.getUserEventType());

    @Override
    public String execute(Update update) {
        return HELP_MESSAGE;
    }
}