package com.lorenzon.task_tracker_java_spring.domain.exception;

public class TaskException extends RuntimeException {

    public TaskException(String message) {
        super(message);
    }
}
