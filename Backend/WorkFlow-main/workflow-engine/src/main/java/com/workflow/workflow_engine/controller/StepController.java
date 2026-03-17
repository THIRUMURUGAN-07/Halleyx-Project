package com.workflow.workflow_engine.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import com.workflow.workflow_engine.entity.Step;
import com.workflow.workflow_engine.service.StepService;
import com.workflow.workflow_engine.dto.CreateStepRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/workflows")
@RequiredArgsConstructor
public class StepController {

private final StepService stepService;

@PostMapping("/{workflowId}/steps")
public Step addStep(
        @PathVariable String workflowId,
        @RequestBody CreateStepRequest request){

    return stepService.addStep(workflowId, request);
}

@GetMapping("/{workflowId}/steps")
public List<Step> getSteps(@PathVariable String workflowId){

    return stepService.getSteps(workflowId);
}

}
