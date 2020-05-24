package com.zrzhen.logicmachine.factfuntion;

import com.zrzhen.logicmachine.constant.FactValueEnum;
import com.zrzhen.logicmachine.constant.OperatorEnum;
import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.AtomicFact;
import com.zrzhen.logicmachine.util.JsonUtil;
import com.zrzhen.logicmachine.zatis.DbSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

public class R360Sore {


    private static final Logger log = LoggerFactory.getLogger(R360Sore.class);


    public static Method execute;


    public static void init() {
        try {
            execute = R360Sore.class.getMethod("execute", AtomicFact.class, String.class);
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 计算原子事实真值
     *
     * @param atomicFact 原子事实
     * @param paramKey   参数获取标识
     * @return 0：假；1：真；-1：异常；
     */
    public static Integer execute(AtomicFact atomicFact, String paramKey) {

        //获取信用分，通过threadlocal中获取参数，或特定数据库中获取
        DbSource ruleDb = DbEnum.RULE.getDb();

        String sql = "SELECT\n" +
                "\tparams \n" +
                "FROM\n" +
                "\trisk_param \n" +
                "WHERE\n" +
                "\tid = ?";

        Object[] bind = new Object[]{paramKey};

        String params = ruleDb.getString(sql, bind);

        Map<String, String> paramMap = JsonUtil.str2Entity(params, Map.class);

        String userId = paramMap.get("userId");
        String name = paramMap.get("name");
        String identityNo = paramMap.get("identityNo");
        String phone = paramMap.get("phone");

        Integer score = score(userId, name, identityNo, phone);

        String value = atomicFact.getValue();//比较的值
        Integer value2 = Integer.valueOf(value);//进行比较
        Boolean result = operator(score, value2, atomicFact.getOperator());
        if (result == null) {
            log.error("Atomic fact error,atomicFact:{}. paramKey:{}", atomicFact, paramKey);
            return FactValueEnum.EXCEPTION.getCode();
        } else if (result) {
            return FactValueEnum.TRUE.getCode();
        }
        return FactValueEnum.FALSE.getCode();
    }


    public static Boolean operator(Integer score, Integer value, String operator) {
        if (operator.equalsIgnoreCase(OperatorEnum.LARGER.getCode())) {
            return score > value;
        } else if (operator.equalsIgnoreCase(OperatorEnum.LARGER_EQUALS.getCode())) {
            return score >= value;
        } else if (operator.equalsIgnoreCase(OperatorEnum.LESS.getCode())) {
            return score < value;
        } else if (operator.equalsIgnoreCase(OperatorEnum.LESS_EQUALS.getCode())) {
            return score <= value;
        } else if (operator.equalsIgnoreCase(OperatorEnum.EQUALS.getCode())) {
            return score == value;
        }
        return null;
    }


    public static int score(String userId, String name, String identityNo, String phone) {

        return 500;
    }
}
