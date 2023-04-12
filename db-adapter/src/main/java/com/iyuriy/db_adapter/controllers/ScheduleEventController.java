package com.iyuriy.db_adapter.controllers;

import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.common.models.ScheduleEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/schedule")
@RestController
public class ScheduleEventController {

    @PostMapping("/addEvent")
    public String addEvent(ScheduleEventDto scheduleEventDto){
        return "scheduleEventDto return";
    }
}
