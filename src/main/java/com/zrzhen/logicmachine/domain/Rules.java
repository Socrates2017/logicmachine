package com.zrzhen.logicmachine.domain;

import java.util.List;

/**
 * 规则集，及规则策略
 */
public class Rules {

    /**
     * id
     */
    private Long id;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 规则集
     */
    List<Rule> ruleList;

    /**
     * 获取参数的key
     */
    String paramKey;


    /**
     * 执行决策,
     * 成功执行，返回true；执行异常，返回false。
     */
    public boolean execute() {
        if (ruleList != null && ruleList.size() > 0) {
            for (int i = 0; i < ruleList.size(); i++) {
                Rule rule = ruleList.get(i);
                rule.setParamKey(this.paramKey);
                rule.execute();
            }
            return true;
        }
        return false;
    }

    public Rules() {
    }

    public Rules(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }
}
