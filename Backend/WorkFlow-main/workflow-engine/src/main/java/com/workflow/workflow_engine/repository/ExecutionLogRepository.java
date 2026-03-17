package com.workflow.workflow_engine.repository;

import com.workflow.workflow_engine.entity.ExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExecutionLogRepository extends JpaRepository<ExecutionLog,String> {

    List<ExecutionLog> findByExecutionId(String executionId);

}