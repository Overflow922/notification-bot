package com.iyuriy.notification.services;

import com.iyuriy.notification.command.CommandContainer;
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

import static com.iyuriy.notification.common.parser.UserEventType.NO;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationBotService extends TelegramLongPollingCommandBot {

    public static String COMMAND_PREFIX = "/";
    public static final String ERROR_CONVERTING_COMMAND = "Не удалось обработать запрос. Проверьте формат.";

    private final NotificationBotConfiguration configs;
    private final ScheduleParser parser;
    private final RestEventSender sender;

    private final UserRepository userRepository;

    private final CommandContainer commandContainer;

    @Transactional
    @Override
    @SneakyThrows
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message inMess = update.getMessage();
            String text = inMess.getText();
            Long chatId = inMess.getChatId();
            log.info("incoming message from {}", chatId);

//            try {
//                User user = userRepository.findByChatId(chatId);
//
//                if (user == null) {
//                    user = createNewUser(chatId);
//                    log.info("new user saved to database: {}", user);
//                }

                String result;
                if (text.startsWith(COMMAND_PREFIX)) {
                    String commandIdentifier = text.split(" ")[0].toLowerCase();
                    result= commandContainer.findCommand(commandIdentifier).execute(update);
                } else {
                    result=  commandContainer.findCommand(NO.getUserEventType()).execute(update);
                }
                notifyUser(result, chatId);

//                ScheduleEvent event = parser.parseEvent(text, user.getTimeZone());
//
//                event.setUserId(chatId);
//                log.info("Sending event: {}", event);
//                sender.send(event);
//            } catch (Exception e) {
//
//                log.error("Произошла ошибка.", e);
//                notifyUser(ERROR_CONVERTING_COMMAND, chatId);
//            }
        }
    }

//    @Transactional
//    public User createNewUser(Long chatId) {
//
//        User user = User.builder()
//                .chatId(chatId)
//                .timeZone(ZoneId.of("Europe/Moscow"))
//                .createdAt(Instant.now())
//                .build();
//        userRepository.save(user);
//        return user;
//    }

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