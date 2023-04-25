package com.iyuriy.notification.common.util;

import com.iyuriy.notification.common.dto.ScheduleEventDto;
import com.iyuriy.notification.common.models.ScheduleEvent;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class ScheduleEventConvertor {

    private final ModelMapper modelMapper;

    public ScheduleEvent DtoToModel(ScheduleEventDto scheduleEventDto) {
        return modelMapper.map(scheduleEventDto, ScheduleEvent.class);
    }

    public ScheduleEventDto ModelToDto(ScheduleEvent scheduleEvent) {
        return modelMapper.map(scheduleEvent, ScheduleEventDto.class);
    }
}