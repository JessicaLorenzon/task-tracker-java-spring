package com.lorenzon.task_tracker_java_spring.domain.service;

import com.lorenzon.task_tracker_java_spring.domain.exception.TaskException;
import com.lorenzon.task_tracker_java_spring.domain.model.StatusTask;
import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import com.lorenzon.task_tracker_java_spring.domain.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public ResponseEntity<Void> delete(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(taskId);
        return ResponseEntity.noContent().build();
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> findById(Long taskId) {
        return taskRepository.findById(taskId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new TaskException("Task not found!"));
    }

    public ResponseEntity<List<Task>> findByStatus(StatusTask status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        if (tasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }

    @Transactional
    public ResponseEntity<Task> update(Long taskId, Task taskInput) {
        Task taskUpdate = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task not found!"));

        if (taskInput.getDescription() != null) {
            taskUpdate.setDescription(taskInput.getDescription());
        }
        if (taskInput.getStatus() != null) {
            taskUpdate.setStatus(taskInput.getStatus());
        }
        taskUpdate.setUpdatedAt(OffsetDateTime.now());

        taskRepository.save(taskUpdate);
        return ResponseEntity.ok(taskUpdate);
    }
}
