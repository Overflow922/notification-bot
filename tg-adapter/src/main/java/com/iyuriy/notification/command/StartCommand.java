package com.iyuriy.notification.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommand implements Command {

    public final static String START_MESSAGE =
            "Привет!\n"
                    + "Я Notification Telegram Bot.\n"
                    + "Я помогу тебе запланировать событие и оповещу о нем!\n"
                    + "Напиши /help, чтобы узнать как начать";

    @Override
    public String execute(Update update) {
        return START_MESSAGE;
    }

}