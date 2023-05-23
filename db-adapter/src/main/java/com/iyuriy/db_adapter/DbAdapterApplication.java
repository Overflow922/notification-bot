package com.iyuriy.db_adapter;

import com.iyuriy.notification.common.util.ScheduleEventConvertor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.iyuriy.notification.common")
@EnableJpaRepositories
@SpringBootApplication
public class DbAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbAdapterApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ScheduleEventConvertor scheduleEventConvertor(ModelMapper modelMapper){
        return new ScheduleEventConvertor(modelMapper);
    }
}