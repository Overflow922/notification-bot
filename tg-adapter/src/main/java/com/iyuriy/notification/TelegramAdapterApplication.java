package com.iyuriy.notification;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@AllArgsConstructor
@SpringBootApplication
public class TelegramAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramAdapterApplication.class, args);
    }

    private final LongPollingBot bot;

    @EventListener(ApplicationReadyEvent.class)
    @SneakyThrows
    public void registerBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }

}