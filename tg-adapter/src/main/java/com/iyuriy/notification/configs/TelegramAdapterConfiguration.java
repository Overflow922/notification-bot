package com.iyuriy.notification.configs;

import com.iyuriy.notification.common.parser.ScheduleParser;
import com.iyuriy.notification.common.parser.TextScheduleParser;
import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramAdapterConfiguration {

    @Bean
    public ScheduleParser parser() {
        return new TextScheduleParser();
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