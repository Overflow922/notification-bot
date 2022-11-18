package com.iyuriy.notification;

import com.iyuriy.notification.commands.AddPeriodicTimer;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public final class NotificationBot extends TelegramLongPollingCommandBot {

    private final String botName;
    private final String botToken;

    public NotificationBot(String botName, String botToken) {
        this.botName = botName;
        this.botToken = botToken;

        register(new AddPeriodicTimer("periodic", "Add periodic timer"));
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }

    @Override
    public String getBotToken() {
        return null;
    }
}
