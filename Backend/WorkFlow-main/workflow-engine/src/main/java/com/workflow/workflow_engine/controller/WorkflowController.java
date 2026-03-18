package com.workflow.workflow_engine.controller;

import com.workflow.workflow_engine.entity.Workflow;
import com.workflow.workflow_engine.repository.WorkflowRepository;
import com.workflow.workflow_engine.service.WorkflowService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/workflows")
@RequiredArgsConstructor
public class WorkflowController {

    private final WorkflowService workflowService;
    private final WorkflowRepository workflowRepository;

    @PostMapping
    public Workflow createWorkflow(@RequestBody Workflow workflow){
        return workflowService.createWorkflow(workflow);
    }

    @GetMapping
    public List<Workflow> getAllWorkflows(){
        return workflowService.getAllWorkflows();
    }

    @GetMapping("/{id}")
    public Workflow getWorkflow(@PathVariable String id){
        return workflowService.getWorkflowById(id);
    }

    @PutMapping("/{id}")
    public Workflow updateWorkflow(@PathVariable String id,
                                   @RequestBody Workflow workflow){
        return workflowService.updateWorkflow(id, workflow);
    }
    @PutMapping("/{workflowId}/start")
    public Workflow updateStartStep(
            @PathVariable String workflowId,
            @RequestBody Map<String, String> request) {

        String startStepId = request.get("startStepId");

        Workflow workflow = workflowRepository.findById(workflowId)
                .orElseThrow();

        workflow.setStartStepId(startStepId);

        return workflowRepository.save(workflow);
    }

    @DeleteMapping("/{id}")
    public String deleteWorkflow(@PathVariable String id){
        workflowService.deleteWorkflow(id);
        return "Workflow deleted";
    }

}