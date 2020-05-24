package com.zrzhen.logicmachine.domain;

import com.zrzhen.logicmachine.constant.ConnectiveEnum;
import com.zrzhen.logicmachine.constant.FactValueEnum;
import com.zrzhen.logicmachine.dao.AtomicFactDao;
import com.zrzhen.logicmachine.factfuntion.AtomicFactFunctionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author chenanlian
 */
public class FactManager {

    private static final Logger log = LoggerFactory.getLogger(FactManager.class);


    Fact fact;

    /**
     * 获取参数的key
     */
    String paramKey;

    int calculateIndex;

    public FactManager(Fact fact, String paramKey) {
        this.fact = fact;
        this.paramKey = paramKey;
    }

    /**
     * 计算根节点真值
     */
    public Integer calculateRoot() {
        /**
         * 遍历计算，当得到结果时，跳出遍历
         */
        try {
            while (fact.getValue() == FactValueEnum.UNKNOWN.getCode()) {
                try {
                    this.calculate(fact);
                } catch (StopMsgException e) {
                    //仅作流程控制，跳出所有递归，此处可优化
                }
            }
            return fact.getValue();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;

    }


    /**
     * 根据倒数第一层的真值计算倒数第二层的真值
     *
     * @param fact
     */
    public void calculate(Fact fact) {
        //非原子事实，根据子事实计算其真值
        if (fact.getAtomicId() == 0 && fact.getValue() == FactValueEnum.UNKNOWN.getCode()) {
            List<Fact> childFacts = fact.getChildren();
            String connective = fact.getConnective();
            Integer value = FactValueEnum.UNKNOWN.getCode();
            for (int i = 0; i < childFacts.size(); i++) {
                Fact childFact = childFacts.get(i);
                //子节点的真值已知
                if (childFact.getValue() != FactValueEnum.UNKNOWN.getCode()) {
                    if (connective.equalsIgnoreCase(ConnectiveEnum.AND.getCode())) {
                        if (childFact.getValue() == FactValueEnum.FALSE.getCode()) {
                            value = FactValueEnum.FALSE.getCode();
                            fact.setValue(value);
                            fact.setCalculateIndex(calculateIndex++);
                            throw new StopMsgException();
                        } else {
                            value = FactValueEnum.TRUE.getCode();
                        }
                    } else if (connective.equalsIgnoreCase(ConnectiveEnum.OR.getCode())) {
                        if (childFact.getValue() == FactValueEnum.TRUE.getCode()) {
                            value = FactValueEnum.TRUE.getCode();
                            fact.setValue(value);
                            fact.setCalculateIndex(calculateIndex++);
                            throw new StopMsgException();
                        } else {
                            value = FactValueEnum.FALSE.getCode();
                        }
                    } else if (connective.equalsIgnoreCase(ConnectiveEnum.NOT.getCode())) {
                        if (childFact.getValue() == FactValueEnum.TRUE.getCode()) {
                            value = FactValueEnum.FALSE.getCode();
                        } else {
                            value = FactValueEnum.TRUE.getCode();
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

        } else if (fact.getAtomicId() != 0 && fact.getValue() == FactValueEnum.UNKNOWN.getCode()) { //如果为原子事实

            Long atomicId = fact.getAtomicId();

            AtomicFact atomicFact = AtomicFactDao.getById(atomicId);//查询出对应的原子事实
            String atomicFactFunction = atomicFact.getAtomicFactFunction();//要执行的方法名

            try {
                Method method = AtomicFactFunctionEnum.getMethodByName(atomicFactFunction);//根据atomicFactFunction通过枚举获得要执行的方法
                Integer value = (Integer) method.invoke(null, atomicFact, this.paramKey);//静态方法，第一个参数传入null
                fact.setCalculateIndex(calculateIndex++);
                fact.setValue(value);

            } catch (Exception e) {
                e.printStackTrace();
                fact.setValue(FactValueEnum.EXCEPTION.getCode());
            }

        }
    }

    class StopMsgException extends RuntimeException {
    }
}
