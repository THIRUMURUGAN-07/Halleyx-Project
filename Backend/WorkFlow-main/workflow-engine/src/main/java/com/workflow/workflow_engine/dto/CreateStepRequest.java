package com.workflow.workflow_engine.dto;
import lombok.Data;

@Data
public class CreateStepRequest {
	
	private String name;

    private String stepType;

    private Integer stepOrder;

    private String metadata;

}
