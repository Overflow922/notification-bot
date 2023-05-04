package com.iyuriy.notification.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.*;

@Service
public class HelpCommand implements Command {

    public static final String HELP_MESSAGE = String.format(
            "Доступные команды:\n\n" +
                    "%s - старт\n\n" +
                    "%s - получить помощь\n\n" +
                    "%s - Добавить задание\n\n" +
                    "%s - Ввести Time Zone\n\n" +
                    "%s - приостановить работу\n\n",
            START.getUserEventType(),
            HELP.getUserEventType(),
            ADD.getUserEventType(),
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