package com.lorenzon.task_tracker_java_spring.api.controller;

import com.lorenzon.task_tracker_java_spring.domain.exception.TaskException;
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

    @GetMapping
    public List<Task> list() {
        return taskService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task add(@Valid @RequestBody Task task) {
        task.setStatus(StatusTask.TODO);
        task.setCreatedAt(OffsetDateTime.now());
        return taskService.save(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable Long taskId) {
        return taskService.delete(taskId);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> update(@PathVariable Long taskId, @Valid @RequestBody Task task) {
        return taskService.update(taskId, task);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> searchById(@PathVariable Long taskId) {
        return taskService.findById(taskId);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> searchByStatus(@PathVariable StatusTask status) {
        return taskService.findByStatus(status);
    }

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<String> capture(TaskException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
