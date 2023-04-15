package com.iyuriy.notification.services;

import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.common.parser.UserEvent;
import com.iyuriy.notification.configs.NotificationBotConfiguration;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@AllArgsConstructor
public final class NotificationBotService extends TelegramLongPollingCommandBot {

    private final NotificationBotConfiguration configs;
    private final ScheduleParser parser;


    @Override
    @SneakyThrows
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMess = update.getMessage();
            String text = inMess.getText();
            UserEvent event = parser.parseEvent(text);
            String chatId = inMess.getChatId().toString();
            String response = "hello";
            SendMessage outMess = new SendMessage();

            outMess.setChatId(chatId);
            outMess.setText(response);

            //Отправка в чат
            execute(outMess);
        }
    }

    @Override
    public String getBotUsername() {
        return configs.getBotName();
    }

    @Override
    public String getBotToken() {
        return configs.getBotToken();
    }
}
