package com.workflow.workflow_engine.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workflow.workflow_engine.dto.MyExecutionDto;
import com.workflow.workflow_engine.engine.WorkflowExecutor;
import com.workflow.workflow_engine.entity.Approval;
import com.workflow.workflow_engine.entity.Execution;
import com.workflow.workflow_engine.entity.ExecutionLog;
import com.workflow.workflow_engine.entity.User;
import com.workflow.workflow_engine.entity.Workflow;
import com.workflow.workflow_engine.repository.ApprovalRepository;
import com.workflow.workflow_engine.repository.ExecutionLogRepository;
import com.workflow.workflow_engine.repository.ExecutionRepository;
import com.workflow.workflow_engine.repository.UserRepository;
import com.workflow.workflow_engine.repository.WorkflowRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ExecutionService {

    private final ExecutionRepository executionRepository;
    private final ExecutionLogRepository executionLogRepository;
    private final WorkflowExecutor workflowExecutor;
    private final UserRepository userRepository;
    private final WorkflowRepository workflowRepository;
    private final ApprovalRepository approvalRepository;
 
    private final ObjectMapper objectMapper = new ObjectMapper();

    // START WORKFLOW
    public Execution startExecution(Execution execution,String email){
    	
    	User user = userRepository.findByEmail(email).get();
    	execution.setUserId(user.getId());
    	
		Workflow workflow = workflowRepository.findById(execution.getWorkflowId()).get();
		Approval approval = new Approval();
		if(workflow.getName().equals("Leave Approval")) {
			int value = getValueFromData(execution.getData());
			User manager = userRepository.findByRole("MANAGER");
			if(value >= 5) {
				User hr = userRepository.findByRole("HR");
				approval.setApproverId1(hr.getId());
				approval.setApproverId2(manager.getId());
				approval.setApprovalStatus1(false);
				approval.setApprovalStatus2(false);
				approval.setUserId(user.getId());
				approval.setWorkflowId(workflow.getId());
			}
			else {
				approval.setApproverId2(manager.getId());
				approval.setApprovalStatus2(false);
				approval.setUserId(user.getId());
				approval.setWorkflowId(workflow.getId());
			}
			approvalRepository.save(approval);
			
		}
		else {
			int value = getValueFromData(execution.getData());
			User bankAssistantManager = userRepository.findByRole("BANK_ASSISTANT_MANAGER");
			if(value >= 30000) {
				User bankManager = userRepository.findByRole("BANK_MANAGER");
				approval.setApproverId1(bankManager.getId());
				approval.setApproverId2(bankAssistantManager.getId());
				approval.setApprovalStatus1(false);
				approval.setApprovalStatus2(false);
				approval.setUserId(user.getId());
				approval.setWorkflowId(workflow.getId());
			}
			else {
				approval.setApproverId2(bankAssistantManager.getId());
				approval.setApprovalStatus2(false);
				approval.setUserId(user.getId());
				approval.setWorkflowId(workflow.getId());
			}
			approvalRepository.save(approval);
			
		}
        execution.setStatus("IN_PROGRESS");
        executionRepository.save(execution);
//        MyExecutionDto myExecutionDto = new MyExecutionDto();
//        
//        myExecutionDto.setExectuion(execution);
//        myExecutionDto.setWorkflow(workflow);

        return  execution;
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

	public List<MyExecutionDto> getUserExecution(String email) {
		// TODO Auto-generated method stub
		
		User user= userRepository.findByEmail(email).get();
		List<Execution> executions = executionRepository.findByUserId(user.getId());
		List<MyExecutionDto> myExecutions = new ArrayList<>();
		for(Execution execution:executions) {
			Workflow workflow = workflowRepository.findById(execution.getWorkflowId()).get();
			if(workflow == null) continue;
			MyExecutionDto myExecutionDto = new MyExecutionDto(execution,workflow);
			myExecutions.add(myExecutionDto);
		}
		
		return myExecutions;
		
	}
	

	@SuppressWarnings("unchecked")
	public int getValueFromData(String data) {

	    try {
	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, Object> map = mapper.readValue(data, Map.class);

	        Object value = map.get("amount");

	        if (value == null) {
	            value = map.get("days"); // 🔥 handle leave case
	        }

	        if (value == null) {
	            throw new RuntimeException("No valid key found in data");
	        }

	        return ((Number) value).intValue();

	    } catch (Exception e) {
	        e.printStackTrace();
	        return 0;
	    }
	}

}