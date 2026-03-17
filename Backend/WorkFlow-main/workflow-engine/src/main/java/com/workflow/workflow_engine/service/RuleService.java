package com.workflow.workflow_engine.service;

import com.workflow.workflow_engine.entity.Rule;
import com.workflow.workflow_engine.repository.RuleRepository;
import com.workflow.workflow_engine.dto.CreateRuleRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RuleService {

    private final RuleRepository ruleRepository;

    public Rule addRule(String stepId, CreateRuleRequest request){

        Rule rule = Rule.builder()
                .stepId(stepId)
                .ruleCondition(request.getCondition())
                .nextStepId(request.getNextStepId())
                .priority(request.getPriority())
                .build();

        return ruleRepository.save(rule);
    }

    public List<Rule> getRules(String stepId){
        return ruleRepository.findByStepIdOrderByPriority(stepId);
    }
}
