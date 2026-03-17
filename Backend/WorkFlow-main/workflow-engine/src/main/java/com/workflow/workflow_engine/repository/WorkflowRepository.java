package com.workflow.workflow_engine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workflow.workflow_engine.entity.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow,String> {
}
