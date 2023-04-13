package com.iyuriy.notification.dbworker;

import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
@EntityScan("com.iyuriy.db_adapter")
@EnableJpaRepositories
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
