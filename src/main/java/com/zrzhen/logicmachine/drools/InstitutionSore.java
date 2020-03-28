package com.zrzhen.logicmachine.drools;

import com.zrzhen.logicmachine.domain.AtomicFact;
import com.zrzhen.logicmachine.domain.AtomicFactParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.List;

public class InstitutionSore {


    private static final Logger log = LoggerFactory.getLogger(InstitutionSore.class);


    public static Method execute;

    {
        try {
            execute = InstitutionSore.class.getMethod("execute", AtomicFact.class, List.class);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }


    public static Byte execute(AtomicFact atomicFact, List<AtomicFactParam> atomicFactParamList) {

        //获取信用分，通过threadlocal中获取参数，或特定数据库中获取
        Integer score = 10;

        String value = atomicFact.getValue();

        Integer value2 = Integer.valueOf(value);
        Boolean result = operator(score, value2, atomicFact.getOperator());
        if (result == null) {
            return 0;
        } else if (result) {
            return 1;
        }
        return -1;
    }


    public static Boolean operator(Integer score, Integer value, String operator) {
        if (operator.equalsIgnoreCase(">")) {
            return score > value;
        } else if (operator.equalsIgnoreCase(">=")) {
            return score >= value;
        } else if (operator.equalsIgnoreCase("<")) {
            return score < value;
        } else if (operator.equalsIgnoreCase("<=")) {
            return score <= value;
        } else if (operator.equalsIgnoreCase("=")) {
            return score == value;
        }
        return null;
    }
}
