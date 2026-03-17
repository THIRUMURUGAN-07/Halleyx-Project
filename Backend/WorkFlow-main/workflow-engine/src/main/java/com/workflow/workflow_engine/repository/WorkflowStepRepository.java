package com.workflow.workflow_engine.repository;

import com.workflow.workflow_engine.entity.WorkflowStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WorkflowStepRepository extends JpaRepository<WorkflowStep, UUID> {

    WorkflowStep findByWorkflowIdAndStepOrder(UUID workflowId, int stepOrder);

}