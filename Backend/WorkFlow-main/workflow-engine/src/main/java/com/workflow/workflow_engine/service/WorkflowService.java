package com.workflow.workflow_engine.service;

import com.workflow.workflow_engine.entity.Workflow;
import com.workflow.workflow_engine.repository.WorkflowRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    public Workflow createWorkflow(Workflow workflow){

        workflow.setVersion(1);
        workflow.setIsActive(true);

        return workflowRepository.save(workflow);
    }

    public List<Workflow> getAllWorkflows(){
        return workflowRepository.findAll();
    }

    public Workflow getWorkflowById(String id){
        return workflowRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found"));
    }

    public Workflow updateWorkflow(String id, Workflow workflow){

        Workflow existing = getWorkflowById(id);

        existing.setName(workflow.getName());
        existing.setIsActive(workflow.getIsActive());

        return workflowRepository.save(existing);
    }

    public void deleteWorkflow(String id){
        workflowRepository.deleteById(id);
    }

}