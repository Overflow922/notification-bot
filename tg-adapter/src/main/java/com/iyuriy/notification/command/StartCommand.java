package com.iyuriy.notification.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.START;

@Service
public class StartCommand implements Command {

    public final static String START_MESSAGE = """
            Я Notification Telegram Bot.
            Я помогу тебе запланировать событие и оповещу о нем!
            Напиши /help, чтобы узнать как начать;
            """;

    public StartCommand() {
    }

    @Override
    public String commandType() {
        return START.getUserEventType();
    }

    @Override
    public String execute(Update update) {
        String name = update.getMessage().getChat().getFirstName();

        return String.format("""
                        Привет, %s!\s
                        %s
                        """,
                name, START_MESSAGE);
    }
}