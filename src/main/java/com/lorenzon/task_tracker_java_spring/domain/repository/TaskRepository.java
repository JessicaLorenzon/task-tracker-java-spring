package com.lorenzon.task_tracker_java_spring.domain.repository;

import com.lorenzon.task_tracker_java_spring.domain.model.StatusTask;
import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(StatusTask status);
}
