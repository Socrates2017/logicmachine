package com.zrzhen.logicmachine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zrzhen.logicmachine.constant.FactValueEnum;
import com.zrzhen.logicmachine.zatis.anno.Column;

/**
 * 规则
 */
public class Rule {

    private Long id;

    /**
     * 事实
     */
    Fact fact;

    @Column(name = "fact_id")
    @JsonProperty("fact_id")
    private Long factId;

    /**
     * 事实满足的时候，执行的结果
     */
    Action action;

    @Column(name = "action_id")
    @JsonProperty("action_id")
    private Long actionId;

    /**
     * 获取参数的key
     */
    String paramKey;


    /**
     * 规则执行
     *
     * @return
     */
    public boolean execute() {
        /**
         * 计算事实真值，1为真；0为假
         */
        Integer result = new FactManager(fact, this.paramKey).calculateRoot();
        if (result == FactValueEnum.TRUE.getCode()) {
            action.setParamKey(paramKey);
            action.execute();
            return true;
        }
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Fact getFact() {
        return fact;
    }

    public Long getFactId() {
        return factId;
    }

    public void setFactId(Long factId) {
        this.factId = factId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }
}
