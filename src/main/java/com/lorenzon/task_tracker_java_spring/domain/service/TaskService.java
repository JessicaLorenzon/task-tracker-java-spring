package com.lorenzon.task_tracker_java_spring.domain.service;

import com.lorenzon.task_tracker_java_spring.domain.exception.TaskException;
import com.lorenzon.task_tracker_java_spring.domain.model.StatusTask;
import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import com.lorenzon.task_tracker_java_spring.domain.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        taskRepository.deleteById(taskId);
    }

    public Task findById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task not found!"));
    }

    public List<Task> findByStatus(StatusTask status) {
        return taskRepository.findByStatus(status);
    }
}
