package com.workflow.workflow_engine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.workflow.workflow_engine.dto.PendingResponseDto;
import com.workflow.workflow_engine.entity.Approval;
import com.workflow.workflow_engine.entity.Execution;
import com.workflow.workflow_engine.entity.User;
import com.workflow.workflow_engine.entity.Workflow;
import com.workflow.workflow_engine.repository.ApprovalRepository;
import com.workflow.workflow_engine.repository.ExecutionRepository;
import com.workflow.workflow_engine.repository.UserRepository;
import com.workflow.workflow_engine.repository.WorkflowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApprovalService {

	private final UserRepository userRepository;
	private final ApprovalRepository approvalRepository;
	private final WorkflowRepository workflowRepository;
	private final ExecutionRepository executionRepsitory;
	private final ExecutionService executionService;
	

	public List<PendingResponseDto> getMyPendingDetails(String email) {
		// TODO Auto-generated method stub

		System.out.println("Inside service==============================================================================================");
		User user = userRepository.findByEmail(email).get();
		System.out.println("After user ==============================================================================================" + user.getRole());
		System.out.println("After user ==============================================================================================" + user.getId());
		
		List<Approval> approvals = new ArrayList<>();
		if (user.getRole().equals("MANAGER") || user.getRole().equals("ASSISTANT_MANAGER")) {
			approvals = approvalRepository.findByApproverId2(user.getId());
			
		} 
		else {
			approvals = approvalRepository.findByApproverId1(user.getId());
		}
		System.out.println("Approval ==============================================================================================" + approvals.size());
		List<PendingResponseDto> pendingResponses = new ArrayList<>();

		for (Approval approval : approvals) {
//			User user1 = approvalRepository.findByUserId(approval.getUserId());
			Workflow workflow = workflowRepository.findById(approval.getWorkflowId()).get();
			System.out.println("=============================== GOT WORK FLOW  ===============================================================" + workflow.getName());
			Execution execution = executionRepsitory.findByWorkflowId(workflow.getId());
			System.out.println("=============================== GOT EXECUTION  ===============================================================" + execution.getStatus());
			int val = executionService.getValueFromData(execution.getData());
			String value = String.valueOf(val);
			PendingResponseDto pendingResponseDto = new PendingResponseDto();
			pendingResponseDto.setName(workflow.getName());
			pendingResponseDto.setId(approval.getId());
			if (user.getRole().equals("MANAGER") || user.getRole().equals("HR")) {
				pendingResponseDto.setDays(value);
				System.out.println("=============================== Set Leave details  ===============================================================" );
				
			} else {
				pendingResponseDto.setAmount(value);
				System.out.println("=============================== Set bank details."
						+ " details  ===============================================================" );
			}

			pendingResponses.add(pendingResponseDto);
			System.out.println("After pending response ==============================================================================================" + pendingResponseDto);
		}
		
		
		System.out.println("After all ==============================================================================================" + pendingResponses.size());
		return pendingResponses;
	}

	public void approve(String email, String id) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByEmail(email).get();
		Approval approval = approvalRepository.findByUserId(user.getId()).get(0);
		Execution execution = executionRepsitory.findByWorkflowId(approval.getWorkflowId());
		if(user.getRole().equals("MANAGER") || user.getRole().equals("ASSISANT_MANAGER")) {
			approval.setApprovalStatus2(true);
		}
		else {
			approval.setApprovalStatus1(true);
		}
		
		if(approval.getApprovalStatus1() == true && approval.getApprovalStatus2() == true) {
			approval.setApprovalStatus("Completed");
			execution.setStatus("Completed");
		}
		else if(approval.getApprovalStatus1() == null && approval.getApprovalStatus2() == true) {
			approval.setApprovalStatus("completed");
			execution.setStatus("Completed");
		}
		else {
			approval.setApprovalStatus("In_PROGRESS");
		}
		
	}

	public void reject(String email, String id) {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByEmail(email).get();
		Approval approval = approvalRepository.findByUserId(user.getId()).get(0);
		Execution execution = executionRepsitory.findByWorkflowId(approval.getWorkflowId());
		if(user.getRole().equals("MANAGER") || user.getRole().equals("ASSISANT_MANAGER")) {
			approval.setApprovalStatus2(true);
		}
		else {
			approval.setApprovalStatus1(true);
		}
		execution.setStatus("Completed");
		approval.setApprovalStatus("REJECTED");
		
	}

}
