package com.workflow.workflow_engine.dto;

import lombok.Data;

@Data
public class ExecutionRequest {

    private String workflowId;

    private String data;
}