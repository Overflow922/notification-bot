package com.iyuriy.notification.command;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.models.User;
import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.repositories.UserRepository;
import com.iyuriy.notification.services.RestEventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class AddCommand implements Command {

    private final static String ANSWER = "Событие запланировано";

    @Autowired
    private ScheduleParser parser;

    @Autowired
    private RestEventSender sender;

    @Autowired
    private UserRepository userRepository;

    public AddCommand() {
    }


    @Override
    public String execute(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        User user = userRepository.findByChatId(chatId);

        ScheduleEvent event = parser.parseEvent(text, user.getTimeZone());
        event.setUserId(chatId);
        log.info("Sending event: {}", event);
        sender.send(event);

        return ANSWER;
    }
}