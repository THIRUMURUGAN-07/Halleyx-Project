package com.workflow.workflow_engine.service;

import com.workflow.workflow_engine.entity.Step;
import com.workflow.workflow_engine.repository.StepRepository;
import com.workflow.workflow_engine.dto.CreateStepRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StepService {
	private final StepRepository stepRepository;

    public Step addStep(String workflowId, CreateStepRequest request){

        Step step = Step.builder()
                .workflowId(workflowId)
                .name(request.getName())
                .stepType(request.getStepType())
                .stepOrder(request.getStepOrder())
                .metadata(request.getMetadata())
                .build();

        return stepRepository.save(step);
    }

    public List<Step> getSteps(String workflowId){
        return stepRepository.findByWorkflowId(workflowId);
    }
}
