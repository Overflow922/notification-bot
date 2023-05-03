package com.iyuriy.notification.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {

    public static final String STOP_MESSAGE =
            "Удалил пользователя \uD83D\uDE1F.\n" +
            "Ты всегда можешь вернуться нажав /start";

    @Override
    public String execute(Update update) {
//        restEventSender.send(update, STOP_MESSAGE);
//        userRepository.findByChatId(update)
//                .ifPresent(it -> {
//                    it.setActive(false);
//                    userRepository.save(it);
//                });
        return STOP_MESSAGE;
    }
}