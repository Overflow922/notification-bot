package com.iyuriy.notification.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RestEventSenderConfiguration {

    @Value("${db-adapter-rest.url}")
    private String url;
}
