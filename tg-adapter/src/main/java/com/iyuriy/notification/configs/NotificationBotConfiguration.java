package com.iyuriy.notification.configs;

import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.common.parser.TextScheduleParser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class NotificationBotConfiguration {
    @Value("${telegram.bot.name}")
    private String botName;

    @Value("${telegram.bot.token}")
    private String botToken;


    @Bean
    public ScheduleParser parser() {
        return new TextScheduleParser();
    }
}
