package com.iyuriy.notification.command;

import com.iyuriy.notification.common.models.User;
import com.iyuriy.notification.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.Instant;
import java.time.ZoneId;
@Slf4j
@Component
public class StartCommand implements Command {

//    private final RestEventSender restEventSender;
//

    private final UserRepository userRepository;

    public final static String START_MESSAGE =
             "Привет!\n"
                    + "Я Notification Telegram Bot.\n"
                    + "Я помогу тебе запланировать событие и оповещу о нем!\n"
                    + "Напиши /help, чтобы узнать как начать";

    public StartCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



//    @Autowired
//    public StartCommand(UserRepository userRepository, RestEventSender restEventSender) {
//        this.userRepository = userRepository;
//        this.restEventSender = restEventSender;
//    }

//    @Override
//    public void execute(Update update) {
//        restEventSender.send(update.getMessage().getChatId().toString(), START_MESSAGE);
//    }

    @Override
    public String execute(Update update) {

      Long chatId = update.getMessage().getChatId();

//        try {
            User user = userRepository.findByChatId(chatId);

            if (user == null) {
                user = createNewUser(chatId);
                log.info("new user saved to database: {}", user);
            }
        return START_MESSAGE;
    }

    @Transactional
    public User createNewUser(Long chatId) {

        User user = User.builder()
                .chatId(chatId)
                .timeZone(ZoneId.of("Europe/Moscow"))
                .createdAt(Instant.now())
                .build();
        userRepository.save(user);
        return user;
    }
}