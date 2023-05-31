package com.iyuriy.notification.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class RestEventSenderConfiguration {

    @Value("${db-adapter-rest.url-schedule}")
    private String urlSchedule;

    @Value("${db-adapter-rest.url-user-events}")
    private String urlUserEvents;

    @Value("${db-adapter-rest.url-user-delete}")
    private String urlUserDelete;

}