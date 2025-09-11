package com.lorenzon.task_tracker_java_spring.api.exceptionhandler;

import com.lorenzon.task_tracker_java_spring.domain.exception.TaskException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<String> capture(TaskException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
