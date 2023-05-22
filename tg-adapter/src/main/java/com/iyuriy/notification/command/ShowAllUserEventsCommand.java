package com.iyuriy.notification.command;

import com.iyuriy.notification.services.RestEventSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.iyuriy.notification.common.parser.UserEventType.ALL_USER_EVENTS;

@Slf4j
@Service
public class ShowAllUserEventsCommand implements Command {

    public static final String EVENTS =
            "Список запланированных событий: ";

    private final RestEventSender sender;

    @Autowired
    public ShowAllUserEventsCommand(RestEventSender sender) {
        this.sender = sender;
    }

    @Override
    public String commandType() {
        return ALL_USER_EVENTS.getUserEventType();
    }

    @Override
    public String execute(Update update) {
        Long chatId = update.getMessage().getChatId();
        log.info("Показаны запланированные сообщения от пользователя chatId={}", chatId);
        List<String> events = sender.getUserEvents(chatId);

        return EVENTS + "\n" + events;
    }
}