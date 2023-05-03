package com.iyuriy.notification.command;

import com.iyuriy.notification.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {

    private final UserRepository userRepository;
    public static final String STOP_MESSAGE =
            "Удалил пользователя \uD83D\uDE1F.\n" +
                    "Ты всегда можешь вернуться нажав /start";


    public StopCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(Update update) {
//        deleteUser(update.getMessage().getChatId());
        return STOP_MESSAGE;
    }

    @Transactional
    public void deleteUser(Long chatId) {
        userRepository.deleteByChatId(chatId);
    }
}