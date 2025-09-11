package com.lorenzon.task_tracker_java_spring.domain.service;

import com.lorenzon.task_tracker_java_spring.api.model.input.TaskUpdateInput;
import com.lorenzon.task_tracker_java_spring.domain.exception.TaskException;
import com.lorenzon.task_tracker_java_spring.domain.model.StatusTask;
import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import com.lorenzon.task_tracker_java_spring.domain.repository.TaskRepository;
import lombok.AllArgsConstructor;
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
    public void delete(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new TaskException("Task not found!");
        }
        taskRepository.deleteById(taskId);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task not found!"));
    }

    public List<Task> findByStatus(StatusTask status) {
        return taskRepository.findByStatus(status);
    }

    @Transactional
    public Task update(Long taskId, TaskUpdateInput taskUpdateInput) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task not found!"));

        if (taskUpdateInput.getDescription() != null) {
            task.setDescription(taskUpdateInput.getDescription());
        }
        if (taskUpdateInput.getStatus() != null) {
            task.setStatus(taskUpdateInput.getStatus());
        }
        task.setUpdatedAt(OffsetDateTime.now());

        return taskRepository.save(task);
    }
}
