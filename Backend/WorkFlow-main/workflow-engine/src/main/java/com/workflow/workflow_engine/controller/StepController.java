package com.workflow.workflow_engine.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.workflow.workflow_engine.entity.Step;
import com.workflow.workflow_engine.service.StepService;
import com.workflow.workflow_engine.dto.CreateStepRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/steps")
@RequiredArgsConstructor
public class StepController {

	private final StepService stepService;

	@PostMapping("/{stepId}/step")
	public Step addStep(@PathVariable String stepId, @RequestBody Step step) {
		System.out.print(step.getWorkflowId());
		return stepService.addStep(stepId, step);
	}

	@PostMapping
	public Step addStepBefore(@RequestBody Step step) {
		System.out.print(step);
		return stepService.addStepBefore(step);
	}

	@PutMapping("/{id}")
	public Step updateStepWorkflow(@PathVariable String id, @RequestBody Step step) {

		return stepService.updateStepWorkflow(id, step);

	}

	@GetMapping("/{workflowId}/steps")
	public List<Step> getSteps(@PathVariable String workflowId) {

		return stepService.getSteps(workflowId);
	}

}
