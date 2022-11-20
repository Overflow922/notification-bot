package com.iyuriy.notification.dbworker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class IdClass {
    @Id
    @GeneratedValue
    private Long id;
}
