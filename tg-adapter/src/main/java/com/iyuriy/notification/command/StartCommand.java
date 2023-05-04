package com.iyuriy.notification.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommand implements Command {

    public final static String START_MESSAGE = """
            Я Notification Telegram Bot.
            Я помогу тебе запланировать событие и оповещу о нем!
            Напиши /help, чтобы узнать как начать;
            """;

    public StartCommand() {
    }

    @Override
    public String execute(Update update) {
        String name = update.getMessage().getChat().getFirstName();
       // String location = String.valueOf(update.getMessage().getChat().getLocation());

      //  String latitude = String.valueOf(update.getMessage().getChat().  getLatitude());
        //  String longitude = String.valueOf(update.getMessage().getLocation().getLongitude());

//        String location = String.valueOf(update.getMessage().getLocation());
        return String.format("""
                        Привет, %s!\s
                        %s
                        """,
                name, START_MESSAGE);
    }
}