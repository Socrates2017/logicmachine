package com.zrzhen.logicmachine.domain;

import com.zrzhen.logicmachine.action.ActionEnum;

import java.lang.reflect.Method;
import java.util.List;

public class Action {

    private Long id;

    /**
     * 结果类型，0：执行方法；1：执行规则；
     */
    private Integer type;

    /**
     * 执行的方法，输出结果
     */
    String result;

    /**
     * 规则集
     */
    List<Rule> ruleList;

    /**
     * 获取参数的key
     */
    String paramKey;


    public boolean execute() {

        if (type == 0) {
            try {
                Method method = ActionEnum.getMethodByName(result);
                method.invoke(null, this.paramKey);//静态方法，第一个参数传入null
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {//执行下一个环节规则集
            for (int i = 0; i < ruleList.size(); i++) {
                Rule rule = ruleList.get(i);
                rule.setParamKey(this.paramKey);
                rule.execute();
            }
            return true;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }
}
