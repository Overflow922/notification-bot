package com.iyuriy.notification.services;

import com.iyuriy.notification.command.CommandContainer;
import com.iyuriy.notification.common.models.User;
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
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationBotService extends TelegramLongPollingCommandBot {

    public static final String ERROR_CONVERTING_COMMAND = "Не удалось обработать запрос. Проверьте формат.";

    private final NotificationBotConfiguration configs;

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
            log.info("Входящее сообщение incoming message from {}", chatId);

            try {
                User user = userRepository.findByChatId(chatId);
                if (user == null) {
                    user = createNewUser(chatId);
                    log.info("New user saved to database: {}", user);
                }

                String commandIdentifier = text.split(" ")[0].toLowerCase();
                String result = commandContainer.findCommand(commandIdentifier).execute(update);
                notifyUser(result, chatId);
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
            setButtons(message);
            execute(message);
        } catch (Exception e) {
            log.error("Не удалось отправить сообщение {}", message, e);
        }
    }

    public synchronized void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("/start"));
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/timezone"));
        keyboardFirstRow.add(new KeyboardButton("/stop"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("/timezone Europe/Moscow"));
        keyboardSecondRow.add(new KeyboardButton("/timezone Europe/Paris"));
        keyboardSecondRow.add(new KeyboardButton("/add 15:00 test"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
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