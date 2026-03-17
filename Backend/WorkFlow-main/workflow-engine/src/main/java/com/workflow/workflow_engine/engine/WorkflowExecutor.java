package com.workflow.workflow_engine.engine;

import com.workflow.workflow_engine.entity.Rule;
import com.workflow.workflow_engine.repository.RuleRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WorkflowExecutor {

    private final RuleRepository ruleRepository;
    private final RuleEvaluator ruleEvaluator;

    public String getNextStep(String stepId, Map<String,Object> data){

        List<Rule> rules = ruleRepository.findByStepIdOrderByPriority(stepId);

        for(Rule rule : rules){

            boolean result = ruleEvaluator.evaluate(rule.getRuleCondition(),data);

            if(result){
                return rule.getNextStepId();
            }
        }

        return null;
    }

}