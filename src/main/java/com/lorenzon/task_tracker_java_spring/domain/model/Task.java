package com.lorenzon.task_tracker_java_spring.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTask status;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
