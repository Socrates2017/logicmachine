package com.zrzhen.logicmachine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zrzhen.logicmachine.zatis.anno.Column;

public class RulesRule {

    @Column(name = "rules_id")
    @JsonProperty("rules_id")
    private Long rulesId;

    @Column(name = "rule_id")
    @JsonProperty("rule_id")
    private Long ruleId;

    public Long getRulesId() {
        return rulesId;
    }

    public void setRulesId(Long rulesId) {
        this.rulesId = rulesId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }
}
