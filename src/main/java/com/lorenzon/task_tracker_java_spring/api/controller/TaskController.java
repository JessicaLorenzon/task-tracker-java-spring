package com.lorenzon.task_tracker_java_spring.api.controller;

import com.lorenzon.task_tracker_java_spring.api.assembler.TaskAssembler;
import com.lorenzon.task_tracker_java_spring.api.model.TaskRepresentationModel;
import com.lorenzon.task_tracker_java_spring.api.model.input.TaskInput;
import com.lorenzon.task_tracker_java_spring.api.model.input.TaskUpdateInput;
import com.lorenzon.task_tracker_java_spring.domain.model.StatusTask;
import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import com.lorenzon.task_tracker_java_spring.domain.service.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskAssembler taskAssembler;

    @GetMapping
    public List<TaskRepresentationModel> list() {
        return taskAssembler.toCollectionModel(taskService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskRepresentationModel add(@Valid @RequestBody TaskInput taskInput) {
        Task task = taskAssembler.toEntity(taskInput);
        task.setStatus(StatusTask.TODO);
        task.setCreatedAt(OffsetDateTime.now());
        return taskAssembler.toModel(taskService.save(task));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskRepresentationModel> update(@PathVariable Long taskId, @Valid @RequestBody TaskUpdateInput taskUpdateInput) {
        return ResponseEntity.ok(taskAssembler.toModel(taskService.update(taskId, taskUpdateInput)));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskRepresentationModel> searchById(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskAssembler.toModel(taskService.findById(taskId)));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskRepresentationModel>> searchByStatus(@PathVariable StatusTask status) {
        List<Task> tasks = taskService.findByStatus(status);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(taskAssembler.toCollectionModel(tasks));
    }
}
