package com.lorenzon.task_tracker_java_spring.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusTask status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime updatedAt;
}
