package com.workflow.workflow_engine.controller;

import com.workflow.workflow_engine.entity.Rule;
import com.workflow.workflow_engine.service.RuleService;
import com.workflow.workflow_engine.dto.CreateRuleRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/steps")
@RequiredArgsConstructor
public class RuleController {

    private final RuleService ruleService;

    @PostMapping("/{stepId}/rules")
    public Rule addRule(
            @PathVariable String stepId,
            @RequestBody CreateRuleRequest request){

        return ruleService.addRule(stepId, request);
    }

    @GetMapping("/{stepId}/rules")
    public List<Rule> getRules(@PathVariable String stepId){

        return ruleService.getRules(stepId);
    }
}