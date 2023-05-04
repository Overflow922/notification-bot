package com.iyuriy.notification.command;

import com.iyuriy.notification.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.STOP;

@Slf4j
@Service
public class StopCommand implements Command {

    private final UserRepository userRepository;
    public static final String STOP_MESSAGE = """
            Пользователь удален \uD83D\uDE1F.
            Ты всегда можешь вернуться нажав
            /start
            """;

    public StopCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String commandType() {
        return STOP.getUserEventType();
    }

    @Transactional
    @Override
    public String execute(Update update) {
        userRepository.deleteByChatId(update.getMessage().getChatId());
        log.info("Пользователь удален из базы");
        return STOP_MESSAGE;
    }

}