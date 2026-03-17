package com.workflow.workflow_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.workflow.workflow_engine.entity.Step;

@Repository
public interface StepRepository
        extends JpaRepository<Step,String> {

    List<Step> findByWorkflowId(String workflowId);
    Step findByWorkflowIdAndStepOrder(String workflowId,Integer stepOrder);
}
