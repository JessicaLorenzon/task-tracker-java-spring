package com.lorenzon.task_tracker_java_spring.api.controller;

import com.lorenzon.task_tracker_java_spring.domain.model.StatusTask;
import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import com.lorenzon.task_tracker_java_spring.domain.repository.TaskRepository;
import com.lorenzon.task_tracker_java_spring.domain.service.TaskService;
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

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @GetMapping
    public List<Task> list() {
        return taskRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task add(@RequestBody Task task) {
        task.setStatus(StatusTask.TODO);
        task.setCreatedAt(OffsetDateTime.now());
        return taskService.save(task);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> delete(@PathVariable Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            return ResponseEntity.notFound().build();
        }

        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> update(@PathVariable Long taskId, @RequestBody Task task) {
        Task taskUpdate = taskService.findById(taskId);
        taskUpdate.setDescription(task.getDescription());
        taskUpdate.setUpdatedAt(OffsetDateTime.now());

        taskService.save(taskUpdate);
        return ResponseEntity.ok(taskUpdate);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> searchById(@PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> searchByStatus(@PathVariable StatusTask status) {
        List<Task> tasks = taskService.findByStatus(status);

        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(tasks);
    }
}
