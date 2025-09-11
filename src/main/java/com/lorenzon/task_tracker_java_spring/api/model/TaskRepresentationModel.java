package com.lorenzon.task_tracker_java_spring.api.model;

import com.lorenzon.task_tracker_java_spring.domain.model.StatusTask;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class TaskRepresentationModel {

    private Long id;
    private String description;
    private StatusTask status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
