package com.workflow.workflow_engine.repository;

import com.workflow.workflow_engine.entity.Execution;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, String> {
		
	public List<Execution> findByUserId(String userId); 
	
	public Execution findByWorkflowId(String id);
}