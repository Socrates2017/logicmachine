package com.zrzhen.logicmachine.rule;

import com.zrzhen.logicmachine.constant.AtomicFactFunctionEnum;
import com.zrzhen.logicmachine.domain.AtomicFact;
import com.zrzhen.logicmachine.domain.AtomicFactParam;
import com.zrzhen.logicmachine.domain.Fact;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    public byte calculateRoot() {
        /**
         * 遍历计算，当得到结果时，跳出遍历
         */
        while (fact.getValue() == 0) {
            try {
                this.calculate(fact);
            } catch (StopMsgException e) {
                //仅作流程控制，跳出所有递归，此处可优化
            }
        }
        return fact.getValue();
    }


    /**
     * 根据倒数第一层的真值计算倒数第二层的真值
     *
     * @param fact
     */
    public void calculate(Fact fact) {
        //非原子事实，根据子事实计算其真值
        if (fact.getAtomicId() == 0 && fact.getValue() == 0) {
            List<Fact> childFacts = fact.getChildren();
            String connective = fact.getConnective();
            byte value = 0;
            for (int i = 0; i < childFacts.size(); i++) {
                Fact childFact = childFacts.get(i);
                //子节点的真值已知
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

        } else if (fact.getAtomicId() != 0 && fact.getValue() == 0) { //如果为原子事实

            Integer atomicId = fact.getAtomicId();

            AtomicFact atomicFact = new AtomicFact(atomicId);//查询出对应的原子事实
            List<AtomicFactParam> atomicFactParamList = null;//根据原子事实Id查询参数



            String atomicFactFunction = atomicFact.getAtomicFactFunction();//要执行的方法名

            try {
                Method method = AtomicFactFunctionEnum.getMethodByName(atomicFactFunction);//根据atomicFactFunction通过枚举获得要执行的方法
                byte value = (byte) method.invoke(null, atomicFact, atomicFactParamList);//静态方法，第一个参数传入null
                fact.setCalculateIndex(calculateIndex++);
                fact.setValue(value);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    class StopMsgException extends RuntimeException {
    }
}
