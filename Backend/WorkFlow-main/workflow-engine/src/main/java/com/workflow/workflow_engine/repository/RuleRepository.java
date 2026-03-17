package com.workflow.workflow_engine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workflow.workflow_engine.entity.Rule;

public interface RuleRepository extends JpaRepository<Rule, String> {
	List<Rule> findByStepIdOrderByPriority(String stepId);

}
