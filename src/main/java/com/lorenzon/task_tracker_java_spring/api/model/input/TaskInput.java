package com.lorenzon.task_tracker_java_spring.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInput {

    @NotBlank
    @Size(max = 255)
    private String description;
}
