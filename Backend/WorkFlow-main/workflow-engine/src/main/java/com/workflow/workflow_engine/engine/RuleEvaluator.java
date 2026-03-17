package com.workflow.workflow_engine.engine;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RuleEvaluator {

    public boolean evaluate(String ruleCondition, Map<String,Object> data){

        if(ruleCondition == null || ruleCondition.equals("DEFAULT")){
            return true;
        }

        ExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext context = new StandardEvaluationContext();

        data.forEach(context::setVariable);

        return parser
                .parseExpression(ruleCondition)
                .getValue(context, Boolean.class);
    }
}