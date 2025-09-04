package com.lorenzon.task_tracker_java_spring.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Task {

    private Long id;
    private String description;
    private String status;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}
