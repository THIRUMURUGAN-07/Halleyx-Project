package com.workflow.workflow_engine.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "approval_table")
@Data
public class Approval {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private String workflowId;
	
	private Boolean approvalStatus1;
	
	private Boolean approvalStatus2;	
	private String approverId1;
	
	private String approverId2;
	
	
	private String userId;
	
	private String approvalStatus;
	
	

}
