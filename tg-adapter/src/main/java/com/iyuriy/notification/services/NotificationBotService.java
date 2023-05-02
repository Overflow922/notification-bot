package com.iyuriy.notification.services;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.models.User;
import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.configs.NotificationBotConfiguration;
import com.iyuriy.notification.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationBotService extends TelegramLongPollingCommandBot {

    private static final String ERROR_CONVERTING_COMMAND = "Не удалось обработать запрос. Проверьте формат.";

    private final NotificationBotConfiguration configs;
    private final ScheduleParser parser;
    private final RestEventSender sender;

    private final UserRepository userRepository;

    @Transactional
    @Override
    @SneakyThrows
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMess = update.getMessage();
            String text = inMess.getText();
            Long chatId = inMess.getChatId();
            log.info("incoming message from {}", chatId);
            try {
                User user = userRepository.findByChatId(chatId);

                if (user == null) {
                    user = createNewUser(chatId);
                    log.info("new user saved to database: {}", user);
                }
                ScheduleEvent event = parser.parseEvent(text, user.getTimeZone());

                event.setUserId(chatId);
                log.info("Sending event: {}", event);
                sender.send(event);
            } catch (Exception e) {

                log.error("Произошла ошибка.", e);
                notifyUser(ERROR_CONVERTING_COMMAND, chatId);
            }
        }
    }

    @Transactional
    public User createNewUser(Long chatId) {

        User user = User.builder()
                .chatId(chatId)
                .timeZone(ZoneId.of("Europe/Moscow"))
                .createdAt(Instant.now())
                .build();
        userRepository.save(user);
        return user;
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