package com.workflow.workflow_engine.dto;

import com.workflow.workflow_engine.entity.Execution;
import com.workflow.workflow_engine.entity.User;
import com.workflow.workflow_engine.entity.Workflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyExecutionDto {

	private Execution execution;
	
	private Workflow workflow;
}
