package com.zrzhen.logicmachine.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 风控通过
 */
public class RiskConfirm {

    private static final Logger log = LoggerFactory.getLogger(RiskConfirm.class);


    public static Method execute;

    {
        try {
            execute = RiskConfirm.class.getMethod("execute", String.class);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }


    public static int execute(String paramKey) {

        //TODO 回调market系统，修改订单状态
        //TODO 保存风控结果记录

        log.info("风控决策通过。paramKey：{}" + paramKey);
        return 0;

    }
}
