package com.lorenzon.task_tracker_java_spring.domain.repository;

import com.lorenzon.task_tracker_java_spring.domain.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


}
