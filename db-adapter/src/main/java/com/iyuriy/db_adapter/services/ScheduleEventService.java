package com.iyuriy.db_adapter.services;

import com.iyuriy.db_adapter.repositories.ScheduleEventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ScheduleEventService {

    private final ScheduleEventRepository scheduleEventRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ScheduleEventService(ScheduleEventRepository scheduleEventRepository, ModelMapper modelMapper) {
        this.scheduleEventRepository = scheduleEventRepository;
        this.modelMapper = modelMapper;
    }



}
