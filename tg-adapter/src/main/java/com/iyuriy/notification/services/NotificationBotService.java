package com.iyuriy.notification.services;

import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.models.User;
import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.configs.NotificationBotConfiguration;
import com.iyuriy.notification.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.TimeZone;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationBotService extends TelegramLongPollingCommandBot {

    private static final String ERROR_CONVERTING_COMMAND = "Не удалось обработать запрос. Проверьте формат.";

    private final NotificationBotConfiguration configs;
    private final ScheduleParser parser;
    private final RestEventSender sender;

    private final UserRepository userRepository;


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
                Optional<User> user = userRepository.findByChatId(chatId);
                log.info("Find user by userRepository: {}", user);

                if (user.isEmpty()) {
                    User newUser = createNewUser(chatId);
                    log.info("new user saved to database: {}", newUser);
                    chatId = newUser.getChatId();
                } else {
                    chatId = user.get().getChatId();
                }

                if (user.isPresent()) {
                    event.setUserId(user.get().getChatId());
                    log.info("Sending event: {}", event);
                    sender.send(event);
                }

            } catch (Exception e) {
                log.error("Error in processNonCommandUpdate", e);
                // todo we can get exception here. Catch logic should be moved to separate method with exception handling
                SendMessage outMess = new SendMessage();
                outMess.setChatId(chatId);
                outMess.setText(ERROR_CONVERTING_COMMAND);
                execute(outMess);
            }
        }
    }

    public User createNewUser(Long chatId) {
        User newUser = new User();
        newUser.setChatId(chatId);
        newUser.setCreatedAt(Instant.now());
        newUser.setTimeZone(Instant.now());//todo: переписать
        userRepository.save(newUser);
        return newUser;
    }

    public void notifyUser(ScheduleEventDto event) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(event.getUserId().toString());
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