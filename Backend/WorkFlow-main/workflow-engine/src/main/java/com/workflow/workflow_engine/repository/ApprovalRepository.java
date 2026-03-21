package com.workflow.workflow_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workflow.workflow_engine.entity.Approval;

public interface ApprovalRepository extends JpaRepository<Approval,String> {
	
	List<Approval> findByUserId(String id);
	
	List<Approval> findByApproverId2(String id);
	List<Approval> findByApproverId1(String id);
	
	
	

}
