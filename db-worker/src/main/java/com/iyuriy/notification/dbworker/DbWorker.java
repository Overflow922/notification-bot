package com.iyuriy.notification.dbworker;

import com.iyuriy.notification.dbworker.services.FindNewScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DbWorker {

    private final FindNewScheduleServiceImpl findNewScheduleServiceImpl;

    @Autowired
    public DbWorker(FindNewScheduleServiceImpl findNewScheduleServiceImpl) {
        this.findNewScheduleServiceImpl = findNewScheduleServiceImpl;
    }


}
