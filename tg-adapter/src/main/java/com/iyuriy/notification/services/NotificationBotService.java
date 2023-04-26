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
            Long chatId = inMess.getChatId();
            log.info("incoming message from {}", chatId);
            try {
                ScheduleEvent event = parser.parseEvent(text);
                event.setUserId(chatId);
                log.info("Sending event: {}", event);
                sender.send(event);
            } catch (Exception e) {
                log.error("Произошла ошибка.", e);
                notifyUser(ERROR_CONVERTING_COMMAND, chatId);
            }
        }
    }

    public void notifyUser(String text, Long chatId) {
        SendMessage message = SendMessage.builder().chatId(chatId).text(text).build();
        try {
            execute(message);
        } catch (Exception e) {
            log.error("Не удалось отправить сообщение {}", message, e);
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
