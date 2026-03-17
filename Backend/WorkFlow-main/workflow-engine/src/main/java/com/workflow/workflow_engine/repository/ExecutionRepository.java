package com.workflow.workflow_engine.repository;

import com.workflow.workflow_engine.entity.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, String> {
}