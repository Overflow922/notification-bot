package com.iyuriy.notification.command;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.models.User;
import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.repositories.UserRepository;
import com.iyuriy.notification.services.RestEventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.ADD;

@Slf4j
@Service
public class AddCommand implements Command {

    private final static String ANSWER = "Событие запланировано: ";

    private final ScheduleParser parser;

    private final RestEventSender sender;

    private final UserRepository userRepository;

    @Autowired
    public AddCommand(ScheduleParser parser, RestEventSender sender, UserRepository userRepository) {
        this.parser = parser;
        this.sender = sender;
        this.userRepository = userRepository;
    }

    @Override
    public String commandType() {
        return ADD.getUserEventType();
    }

    @Override
    public String execute(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        User user = userRepository.findUserByChatId(chatId);

        ScheduleEvent event = parser.parseEvent(text, user.getTimeZone());
        event.setUserId(chatId);
        log.info("Sending event: {}", event);
        sender.sendEvent(event);

        return ANSWER + text;
    }
}