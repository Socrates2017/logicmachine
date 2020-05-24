package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.Action;
import com.zrzhen.logicmachine.zatis.DbSource;
import com.zrzhen.logicmachine.zatis.Orm;

import java.util.HashMap;
import java.util.Map;

public class ActionDao {

    private static String table = "action";

    private static Orm<Action> orm = new Orm(Action.class);

    private static DbSource db = DbEnum.RULE.getDb();

    public static int insert(Action record) {

        Map<String, Object> valueMap = new HashMap<>();

        if (record.getId() != null) {
            valueMap.put("id", record.getId());
        }
        if (record.getType() != null) {
            valueMap.put("type", record.getType());
        }
        if (record.getResult() != null) {
            valueMap.put("result", record.getResult());
        }
        return db.insertAutocommit(table, valueMap);
    }

    public static Action getById(Long id) {

        String sql = "select * from " + table + " where id =?";
        Object[] bindArgs = new Object[]{id};
        Map<String, Object> result = db.getOne(sql, bindArgs);

        Action record = orm.getEntity(result);
        return record;
    }
}
