package com.workflow.workflow_engine.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;

@Entity
@Data
public class WorkflowStep {

    @Id
    private UUID id;

    private UUID workflowId;

    private String name;

    private String stepType;

    private int stepOrder;

    private String metadata;
}