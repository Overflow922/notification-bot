package com.iyuriy.notification.command;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.models.User;
import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import com.iyuriy.notification.repositories.UserRepository;
import com.iyuriy.notification.services.RestEventSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.ADD;

@AllArgsConstructor
@Slf4j
@Service
public class AddCommand implements Command {

    private final static String ANSWER = "Событие запланировано: ";

    private final ScheduleParser parser;

    private final RestEventSender sender;

    private final UserRepository userRepository;

    private final ScheduleEventConvertor convertor;

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
        sender.sendEvent(convertor.ModelToDto(event));

        return ANSWER + text;
    }
}