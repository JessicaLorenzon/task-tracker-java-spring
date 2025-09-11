package com.lorenzon.task_tracker_java_spring.api.model.input;

import com.lorenzon.task_tracker_java_spring.domain.model.StatusTask;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskUpdateInput {

    @Size(max = 255)
    private String description;

    private StatusTask status;
}
