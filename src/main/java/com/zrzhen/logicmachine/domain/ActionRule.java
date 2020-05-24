package com.zrzhen.logicmachine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zrzhen.logicmachine.zatis.anno.Column;

public class ActionRule {

    @Column(name = "action_id")
    @JsonProperty("action_id")
    private Long actionId;

    @Column(name = "rule_id")
    @JsonProperty("rule_id")
    private Long ruleId;

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
}
