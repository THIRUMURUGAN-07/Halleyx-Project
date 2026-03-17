package com.workflow.workflow_engine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "execution_logs")
@Data
public class ExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String executionId;

    private String stepName;

    private String status;

    private String approverId;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}