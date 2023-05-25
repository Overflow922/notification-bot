package com.iyuriy.notification.dbworker.configs;

import com.iyuriy.notification.common.models.ScheduleEvent;
import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbWorkerConfiguration {

    @Bean
    public ScheduleEvent scheduleEvent() {
        return new ScheduleEvent();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ScheduleEventConvertor scheduleEventConvertor(ModelMapper modelMapper) {
        return new ScheduleEventConvertor(modelMapper);
    }
}