package com.iyuriy.notification.command;

import com.iyuriy.notification.repositories.UserRepository;
import com.iyuriy.notification.services.RestEventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.STOP;

@Slf4j
@Service
public class StopCommand implements Command {

    private final UserRepository userRepository;
    private final RestEventSender restEventSender;

    public static final String STOP_MESSAGE = """
            Пользователь удален \uD83D\uDE1F.
            Ты всегда можешь вернуться нажав
            /start
            """;

    @Autowired
    public StopCommand(UserRepository userRepository, RestEventSender restEventSender) {
        this.userRepository = userRepository;
        this.restEventSender = restEventSender;
    }

    @Override
    public String commandType() {
        return STOP.getUserEventType();
    }

    @Transactional
    @Override
    public String execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        userRepository.deleteUserByChatId(chatId);
        log.info("Пользователь {} удален из базы", chatId);
        restEventSender.deleteUserEvents(chatId);
        log.info("Сообщения пользователя {} удалены из базы", chatId);
        return STOP_MESSAGE;
    }
}