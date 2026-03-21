package com.workflow.workflow_engine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.workflow_engine.dto.ApproveId;
import com.workflow.workflow_engine.dto.PendingResponseDto;
import com.workflow.workflow_engine.service.ApprovalService;

@RestController

@RequestMapping("/approvals")
public class ApprovalController {
	
	@Autowired
	private ApprovalService approvalService;
	
	@GetMapping("/pending")
	public List<PendingResponseDto> getMyPendingDetails(){
		
		String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
		return  approvalService.getMyPendingDetails(email);
	}
	
	@PostMapping("/approve")
	public String approve(@RequestBody ApproveId approveId) {
		String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
		approvalService.approve(email, approveId.getId());
		return "Approved successfully";
	}
	
	@PostMapping("/reject")
	public String reject(@RequestBody ApproveId rejectId) {
		String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
		approvalService.reject(email,rejectId.getId());
		return "rejected";
	}
}
