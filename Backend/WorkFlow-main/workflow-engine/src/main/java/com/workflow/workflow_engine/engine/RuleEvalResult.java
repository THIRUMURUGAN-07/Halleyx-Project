package com.workflow.workflow_engine.engine;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class RuleEvalResult {

    private List<Map<String, Object>> evaluatedRules; // [{rule, result}, ...]
    private String matchedRuleId;
    private String nextStepId;
    private String nextStepName;
    private boolean hasError;
    private String errorMessage;
}
