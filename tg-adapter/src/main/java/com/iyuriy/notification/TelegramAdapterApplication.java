package com.iyuriy.notification;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramAdapterApplication {

    @Autowired
    private LongPollingBot bot;

    public static void main(String[] args) {
        SpringApplication.run(TelegramAdapterApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    @SneakyThrows
    public void registerBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}
