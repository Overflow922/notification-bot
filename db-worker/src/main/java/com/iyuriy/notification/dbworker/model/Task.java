package com.iyuriy.notification.dbworker.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task extends IdClass {
    private String text;
    @Column(name = "original_rq")
    private String originalRequest;
}
