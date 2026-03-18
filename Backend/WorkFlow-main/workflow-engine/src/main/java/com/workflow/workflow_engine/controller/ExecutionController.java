package com.workflow.workflow_engine.controller;

import com.workflow.workflow_engine.entity.Execution;
import com.workflow.workflow_engine.service.ExecutionService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/executions")
@RequiredArgsConstructor
public class ExecutionController {

    private final ExecutionService executionService;

    // START WORKFLOW
    @PreAuthorize("hasRole('USER')")
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
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{executionId}/approve")
    public Execution approve(@PathVariable String executionId,
                             @RequestParam String approverId){

        return executionService.approveStep(executionId,approverId);
    }

    // REJECT STEP
    @PreAuthorize("hasRole('MANAGER')")
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