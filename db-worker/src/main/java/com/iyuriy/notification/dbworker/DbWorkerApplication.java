package com.iyuriy.notification.dbworker;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan("com.iyuriy.notification.common")

@EnableJpaRepositories
@EnableScheduling
@SpringBootApplication
public class DbWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbWorkerApplication.class, args);
    }


    @Bean
    public ScheduleEvent scheduleEvent() {
        return new ScheduleEvent();
    }



}
