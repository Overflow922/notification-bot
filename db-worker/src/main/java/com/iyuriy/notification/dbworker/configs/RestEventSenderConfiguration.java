package com.iyuriy.notification.dbworker.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RestEventSenderConfiguration {

    @Value("${tg-adapter-rest.url}")
    private String urlTgA;

    @Value("${db-adapter-rest.url}")
    private String urlDbA;

}
