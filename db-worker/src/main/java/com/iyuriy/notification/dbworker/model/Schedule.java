package com.iyuriy.notification.dbworker.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "schedule")
public class Schedule extends IdClass {
    private Instant timestamp;

    @OneToOne
    private Task task;
}
