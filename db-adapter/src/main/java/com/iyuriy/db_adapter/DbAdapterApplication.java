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

//    @Autowired
//    ScheduleEventRepository scheduleEventRepository;
//
//@EventListener(value = ApplicationReadyEvent.class)
//    public void createSchedule(){
//        ScheduleEvent schedule = ScheduleEvent.builder()
//                .id(1L)
//                .userId(100L)
//                .notificationText("hello")
//                .originalRq("/add 16:00 hello")
//                .build();
//        scheduleEventRepository.save(schedule);
//    }

}