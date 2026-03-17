package com.workflow.workflow_engine.dto;

import lombok.Data;

@Data
public class CreateRuleRequest {

    private String condition;

    private String nextStepId;

    private Integer priority;

}
