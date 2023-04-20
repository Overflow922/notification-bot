package com.iyuriy.notification.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.configs.NotificationBotConfiguration;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
@AllArgsConstructor
public final class NotificationBotService extends TelegramLongPollingCommandBot {

    private static final String ERROR_CONVERTING_COMMAND = "Не удалось обработать запрос. Проверьте формат.";

    private final NotificationBotConfiguration configs;
    private final ScheduleParser parser;
    private final RestEventSender sender;


    @Override
    @SneakyThrows
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMess = update.getMessage();
            String text = inMess.getText();
            String chatId = inMess.getChatId().toString();
            log.info("incoming message from {}", chatId);
            try {
                ScheduleEvent event = parser.parseEvent(text);
                sender.send(event);
            } catch (Exception e) {
                // todo we can get exception here. Catch logic should be moved to separate method with exception handling
                SendMessage outMess = new SendMessage();
                outMess.setChatId(chatId);
                outMess.setText(ERROR_CONVERTING_COMMAND);
                execute(outMess);
            }
        }
    }

    public void notifyUser(ScheduleEvent event) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(event.getUserId());
        message.setText(event.getNotificationText());
        execute(message);
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
