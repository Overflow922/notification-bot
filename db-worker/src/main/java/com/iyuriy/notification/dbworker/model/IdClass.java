package com.iyuriy.notification.dbworker.model;

import javax.persistence.*;

@MappedSuperclass
public class IdClass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "default_seq")
    @SequenceGenerator(name = "default_seq",
            sequenceName = "default_seq", allocationSize = 1)
    private Long id;
}
