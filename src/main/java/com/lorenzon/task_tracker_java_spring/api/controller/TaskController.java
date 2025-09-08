package com.lorenzon.task_tracker_java_spring.api.controller;

import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import com.lorenzon.task_tracker_java_spring.domain.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    @GetMapping
    public List<Task> list() {
        return taskRepository.findAll();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> search(@PathVariable Long taskId) {
        return taskRepository.findById(taskId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
