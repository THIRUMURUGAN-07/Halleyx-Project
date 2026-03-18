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

    public Step addStep(String stepId, Step request){

    	
        Step step = stepRepository.findById(stepId).get();
        
        step.setWorkflowId(request.getWorkflowId());

        return stepRepository.save(step);
    }

    public List<Step> getSteps(String workflowId){
        return stepRepository.findByWorkflowId(workflowId);
    }

	public Step updateStepWorkflow(String id, Step step) {
	    Step existing = stepRepository.findById(id).orElseThrow();

	    existing.setWorkflowId(step.getWorkflowId());

	    return stepRepository.save(existing);
	}

	public Step addStepBefore(Step step) {
		// TODO Auto-generated method stub
		return stepRepository.save(step);
	}
}
