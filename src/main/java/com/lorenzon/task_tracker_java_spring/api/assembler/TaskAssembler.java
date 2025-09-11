package com.lorenzon.task_tracker_java_spring.api.assembler;

import com.lorenzon.task_tracker_java_spring.api.model.TaskRepresentationModel;
import com.lorenzon.task_tracker_java_spring.api.model.input.TaskInput;
import com.lorenzon.task_tracker_java_spring.api.model.input.TaskUpdateInput;
import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class TaskAssembler {

    private final ModelMapper modelMapper;

    public Task toEntity(TaskInput taskInput) {
        return modelMapper.map(taskInput, Task.class);
    }

    public Task toUpdateEntity(TaskUpdateInput taskUpdateInput) {
        return modelMapper.map(taskUpdateInput, Task.class);
    }

    public TaskRepresentationModel toModel(Task task) {
        return modelMapper.map(task, TaskRepresentationModel.class);
    }

    public List<TaskRepresentationModel> toCollectionModel(List<Task> tasks) {
        return tasks.stream().map(this::toModel).toList();
    }
}
