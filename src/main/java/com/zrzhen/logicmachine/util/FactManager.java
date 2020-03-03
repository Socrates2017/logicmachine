package com.zrzhen.logicmachine.util;

import com.zrzhen.logicmachine.domain.Fact;

import java.util.List;

/**
 * @author chenanlian
 */
public class FactManager {

    Fact fact;

    int calculateIndex;

    public FactManager(Fact fact) {
        this.fact = fact;
    }

    /**
     * 计算根节点真值
     */
    public void calculateRoot() {
        while (fact.getValue() == 0) {
            try {
                this.calculate(fact);
            } catch (StopMsgException e) {
                //仅作流程控制，跳出所有递归，此处可优化
            }
        }
    }

    public void calculateRoot(Fact fact) {
        while (fact.getValue() == 0) {
            try {
                this.calculate(fact);
            } catch (StopMsgException e) {
                //仅作流程控制，跳出所有递归，此处可优化
            }
        }
    }

    /**
     * 根据倒数第一层的真值计算倒数第二层的真值
     *
     * @param fact
     */
    public void calculate(Fact fact) {
        if (fact.getAtomicId() == 0 && fact.getValue() == 0) {
            List<Fact> childFacts = fact.getChildren();
            String connective = fact.getConnective();
            byte value = 0;
            for (int i = 0; i < childFacts.size(); i++) {
                Fact childFact = childFacts.get(i);
                if (childFact.getValue() != 0) {
                    if (connective.equalsIgnoreCase("AND")) {
                        if (childFact.getValue() == -1) {
                            value = -1;
                            fact.setValue(value);
                            fact.setCalculateIndex(calculateIndex++);
                            throw new StopMsgException();
                        } else {
                            value = 1;
                        }
                    } else if (connective.equalsIgnoreCase("OR")) {
                        if (childFact.getValue() == 1) {
                            value = 1;
                            fact.setValue(value);
                            fact.setCalculateIndex(calculateIndex++);
                            throw new StopMsgException();
                        } else {
                            value = -1;
                        }
                    } else if (connective.equalsIgnoreCase("NOT")) {
                        if (childFact.getValue() == 1) {
                            value = -1;

                        } else {
                            value = 1;
                        }
                        fact.setValue(value);
                        fact.setCalculateIndex(calculateIndex++);
                        throw new StopMsgException();
                    }

                } else {
                    this.calculate(childFact);
                }
            }
            fact.setCalculateIndex(calculateIndex++);
            fact.setValue(value);
        }
    }

    class StopMsgException extends RuntimeException {
    }
}
