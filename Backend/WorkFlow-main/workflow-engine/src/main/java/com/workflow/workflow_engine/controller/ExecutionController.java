package com.workflow.workflow_engine.controller;

import com.workflow.workflow_engine.entity.Execution;
import com.workflow.workflow_engine.service.ExecutionService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/executions")
@RequiredArgsConstructor
public class ExecutionController {

    private final ExecutionService executionService;

    // START WORKFLOW
    @PostMapping
    public Execution startExecution(@RequestBody Execution execution){

        return executionService.startExecution(execution);
    }

    // MOVE TO NEXT STEP
    @PostMapping("/{executionId}/next")
    public Execution moveToNext(@PathVariable String executionId){

        return executionService.moveToNextStep(executionId);
    }

    // APPROVE STEP
    @PostMapping("/{executionId}/approve")
    public Execution approve(@PathVariable String executionId,
                             @RequestParam String approverId){

        return executionService.approveStep(executionId,approverId);
    }

    // REJECT STEP
    @PostMapping("/{executionId}/reject")
    public Execution reject(@PathVariable String executionId,
                            @RequestParam String approverId){

        return executionService.rejectStep(executionId,approverId);
    }

    // GET EXECUTION STATUS
    @GetMapping("/{executionId}")
    public Execution getExecution(@PathVariable String executionId){

        return executionService.getExecution(executionId);
    }

}