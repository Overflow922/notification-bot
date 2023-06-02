package com.iyuriy.notification.command;

import com.iyuriy.notification.services.RestEventSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.iyuriy.notification.common.parser.UserEventType.DELETE_EVENT;

@AllArgsConstructor
@Slf4j
@Service
public class DeleteCommand implements Command {

    private final static String ANSWER_YES = "Событие удалено: ";
    private final static String ANSWER_NO = "Такого события в базе нет!";
    private final static String ANSWER_ERROR = "Не удалось удалить событие!";
    private final RestEventSender sender;

    @Override
    public String commandType() {
        return DELETE_EVENT.getUserEventType();
    }

    @Override
    public String execute(Update update) {

        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        HttpStatus statusAnswer = sender.deleteOneUserEvent(chatId, text);

        if (statusAnswer == HttpStatus.OK) {
            return ANSWER_YES + text;
        } else if (statusAnswer == HttpStatus.BAD_REQUEST) {
            return ANSWER_NO;
        } else {
            return ANSWER_ERROR;
        }

    }
}