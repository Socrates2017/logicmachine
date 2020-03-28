package com.zrzhen.logicmachine.drools;

import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.rule.FactManager;

/**
 * 规则
 */
public class Rule {

    /**
     * 事实
     */
    Fact fact;

    /**
     * 事实满足的时候，执行的结果
     */
    Then then;


    /**
     * 规则执行
     * @return
     */
    public boolean excute() {
        /**
         * 计算事实真值，1为真；0为假
         */
        byte result = new FactManager(fact).calculateRoot();
        if (result == 1) {
            then.excute();
            return true;
        }
        return false;
    }

    public Then getThen() {
        return then;
    }

    public void setThen(Then then) {
        this.then = then;
    }

    public Fact getFact() {
        return fact;
    }

    public void setFact(Fact fact) {
        this.fact = fact;
    }
}
