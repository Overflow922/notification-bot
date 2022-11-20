package com.iyuriy.notification.dbworker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DbWorkerApplication {

    @Autowired


    public static void main(String[] args) {
        SpringApplication.run(DbWorkerApplication.class, args);
    }

    @PostConstruct
    void init() {

    }
}
