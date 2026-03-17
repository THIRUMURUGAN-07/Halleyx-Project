package com.workflow.workflow_engine.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workflow.workflow_engine.engine.WorkflowExecutor;
import com.workflow.workflow_engine.entity.Execution;
import com.workflow.workflow_engine.entity.ExecutionLog;
import com.workflow.workflow_engine.repository.ExecutionLogRepository;
import com.workflow.workflow_engine.repository.ExecutionRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExecutionService {

    private final ExecutionRepository executionRepository;
    private final ExecutionLogRepository executionLogRepository;
    private final WorkflowExecutor workflowExecutor;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // START WORKFLOW
    public Execution startExecution(Execution execution){

        execution.setStatus("IN_PROGRESS");

        return executionRepository.save(execution);
    }

    // MOVE TO NEXT STEP
    public Execution moveToNextStep(String executionId){

        Execution execution = executionRepository
                .findById(executionId)
                .orElseThrow(() -> new RuntimeException("Execution not found"));

        Map<String,Object> data;

        try {
            data = objectMapper.readValue(execution.getData(), Map.class);
        }
        catch (Exception e){
            throw new RuntimeException("Invalid execution data");
        }

        String nextStepId = workflowExecutor
                .getNextStep(execution.getCurrentStepId(),data);

        if(nextStepId == null){

            execution.setStatus("COMPLETED");

        }else{

            execution.setCurrentStepId(nextStepId);

        }

        return executionRepository.save(execution);
    }

    // APPROVE STEP
    public Execution approveStep(String executionId,String approverId){

        Execution execution = executionRepository
                .findById(executionId)
                .orElseThrow();

        ExecutionLog log = new ExecutionLog();

        log.setExecutionId(executionId);
        log.setStepName(execution.getCurrentStepId());
        log.setStatus("APPROVED");
        log.setApproverId(approverId);
        log.setStartedAt(LocalDateTime.now());
        log.setEndedAt(LocalDateTime.now());

        executionLogRepository.save(log);

        return moveToNextStep(executionId);
    }

    // REJECT STEP
    public Execution rejectStep(String executionId,String approverId){

        Execution execution = executionRepository
                .findById(executionId)
                .orElseThrow();

        execution.setStatus("REJECTED");

        ExecutionLog log = new ExecutionLog();

        log.setExecutionId(executionId);
        log.setStepName(execution.getCurrentStepId());
        log.setStatus("REJECTED");
        log.setApproverId(approverId);
        log.setStartedAt(LocalDateTime.now());
        log.setEndedAt(LocalDateTime.now());

        executionLogRepository.save(log);

        return executionRepository.save(execution);
    }

    // GET EXECUTION
    public Execution getExecution(String executionId){

        return executionRepository
                .findById(executionId)
                .orElseThrow(() -> new RuntimeException("Execution not found"));
    }

}