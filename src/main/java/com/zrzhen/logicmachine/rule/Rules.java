package com.zrzhen.logicmachine.rule;

import com.zrzhen.logicmachine.drools.Rule;

import java.util.List;

/**
 * 规则集，及规则策略
 */
public class Rules {

    /**
     * id
     */
    private int rulesId;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 规则集
     */
    List<Rule> ruleList;


    /**
     * 执行决策,
     * 成功执行，返回true；执行异常，返回false。
     */
    public boolean excute() {
        if (ruleList != null && ruleList.size() > 0) {
            for (int i = 0; i < ruleList.size(); i++) {
                Rule rule = ruleList.get(i);
                rule.excute();
            }
            return true;
        }
        return false;
    }


    public Rules(int rulesId) {
        this.rulesId = rulesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRulesId() {
        return rulesId;
    }

    public void setRulesId(int rulesId) {
        this.rulesId = rulesId;
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }
}
