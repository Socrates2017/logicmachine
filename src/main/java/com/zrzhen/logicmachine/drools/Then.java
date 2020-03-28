package com.zrzhen.logicmachine.drools;

import com.zrzhen.logicmachine.constant.ActionEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Then {
    private int type;
    Action action;
    List<Rule> ruleList;


    public boolean excute() {

        if (type == 0) {
            //do action


            try {

                Method method = ActionEnum.getMethodByName("");
                method.invoke(null, null);//静态方法，第一个参数传入null
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        } else {//执行下一个环节规则集
            for (int i = 0; i < ruleList.size(); i++) {
                Rule rule = ruleList.get(i);
                rule.excute();
            }
        }
        return false;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }
}
