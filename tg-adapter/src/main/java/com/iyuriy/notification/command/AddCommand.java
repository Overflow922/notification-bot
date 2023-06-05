package com.iyuriy.notification.command;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.models.User;
import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import com.iyuriy.notification.repositories.UserRepository;
import com.iyuriy.notification.services.RestEventSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.ADD;

@AllArgsConstructor
@Slf4j
@Service
public class AddCommand implements Command {

    private final static String ANSWER_YES = "Событие запланировано: ";

    private final static String ANSWER_DUPLICATE = "Это событие уже было запланировано ранее!";

    private final static String ANSWER_NO = "Не удалось запланировать событие!";

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
        log.info("Событие отправлено: {}", event);

        HttpStatus statusAnswer = sender.sendEvent(convertor.ModelToDto(event));

        if (statusAnswer == HttpStatus.OK) {
            return ANSWER_YES + text;
        } else if (statusAnswer == HttpStatus.CONFLICT) {
            return ANSWER_DUPLICATE;
        } else {
            return ANSWER_NO;
        }
    }
  
}