package com.iyuriy.notification.command;

import com.iyuriy.notification.common.models.User;
import com.iyuriy.notification.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.ZoneId;

import static com.iyuriy.notification.services.NotificationBotService.ERROR_CONVERTING_COMMAND;

@Slf4j
@Component
public class TimeZoneCommand implements Command {

    private final UserRepository userRepository;

    public static final String CURRENT_TIME_ZONE = """
             Ваша временная зона:
             """;

    public static final String TIME_ZONE_CHANGED =
            "Временная зона изменена!";

    @Autowired
    public TimeZoneCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String execute(Update update) {

        String text = update.getMessage().getText();

        if (text.equals("/timezone")) {
            return CURRENT_TIME_ZONE+ getUserTimeZone(update.getMessage().getChatId());
        }

        String timeZone = text.split(" ", 2)[1];
        if (timeZone != null) {
            addTimeZone(update.getMessage().getChatId(), timeZone);
            return TIME_ZONE_CHANGED;
        } else return ERROR_CONVERTING_COMMAND;
    }

    @Transactional
    public void addTimeZone(Long chatId, String timeZone) {

        User user = userRepository.findByChatId(chatId);
        user.setTimeZone(ZoneId.of(timeZone));
        log.info("User changed ZoneId: {}", user);
        userRepository.save(user);
    }

    public String getUserTimeZone(Long chatId) {
        User user = userRepository.findByChatId(chatId);
        return user.getTimeZone().toString();
    }
}