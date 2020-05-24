package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.Rule;
import com.zrzhen.logicmachine.domain.Rules;
import com.zrzhen.logicmachine.zatis.DbSource;
import com.zrzhen.logicmachine.zatis.Orm;

import java.util.HashMap;
import java.util.Map;

public class RulesDao {

    private static String table = "rules";

    private static Orm<Rules> orm = new Orm(Rules.class);

    private static DbSource db = DbEnum.RULE.getDb();

    public static int insert(Rules record) {

        Map<String, Object> valueMap = new HashMap<>();

        if (record.getId() != null) {
            valueMap.put("id", record.getId());
        }
        if (record.getName()!= null) {
            valueMap.put("name", record.getName());
        }
        return db.insertAutocommit(table, valueMap);
    }

    public static Rules getById(Long id) {

        String sql = "select * from " + table + " where id =?";
        Object[] bindArgs = new Object[]{id};
        Map<String, Object> result = db.getOne(sql, bindArgs);

        Rules record = orm.getEntity(result);
        return record;
    }
}
