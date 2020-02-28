package com.zrzhen.logicmachine.util;

import com.zrzhen.logicmachine.dao.GodSqlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FunctionUtil {

    private static final Logger log = LoggerFactory.getLogger(FunctionUtil.class);

    @Autowired
    GodSqlMapper godSqlMapper;


    public int larger(String variable, int num, int customerId) {

        String[] variables = variable.split("\\.");
        String table = variables[0];
        String column = variables[1];
        String sql = "SELECT " + column + " FROM " + table + " WHERE customer_id=" + customerId;

        List<Map<String, Object>> result = query(sql);
        Integer age = Integer.valueOf(result.get(0).get(column).toString());

        return age > num ? 1 : 0;
    }

    public List<Map<String, Object>> query(String sqlStr) {
        List<Map<String, Object>> res = godSqlMapper.query(sqlStr);
        return res;
    }
}
