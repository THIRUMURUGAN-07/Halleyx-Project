package com.workflow.workflow_engine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "executions")
@Data
public class Execution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String workflowId;

    private String currentStepId;

    private String status;

    @Column(columnDefinition = "json")
    private String data;
    
    private String userId;
}