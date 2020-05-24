package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.AtomicFact;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.zatis.DbSource;
import com.zrzhen.logicmachine.zatis.Orm;

import java.util.HashMap;
import java.util.Map;

public class FactDao {

    private static String table = "fact";

    private static Orm<Fact> orm = new Orm(Fact.class);

    private static DbSource db = DbEnum.RULE.getDb();

    public static int insert(Fact record) {

        Map<String, Object> valueMap = new HashMap<>();

        if (record.getId() != null) {
            valueMap.put("id", record.getId());
        }
        if (record.getType() != null) {
            valueMap.put("type", record.getType());
        }
        if (record.getAtomicId() != null) {
            valueMap.put("atomic_id", record.getAtomicId());
        }
        if (record.getName() != null) {
            valueMap.put("name", record.getName());
        }

        return db.insertAutocommit(table, valueMap);
    }

    public static Fact getById(Long id) {

        String sql = "select * from " + table + " where id =?";
        Object[] bindArgs = new Object[]{id};
        Map<String, Object> result = db.getOne(sql, bindArgs);

        Fact fact = orm.getEntity(result);
        return fact;
    }

    public static int countByActomicId(Long atomicFact) {

        String sql = "select count(1) from " + table + " where atomic_id =?";
        Object[] bindArgs = new Object[]{atomicFact};
        int result = db.count(sql, bindArgs);
        return result;
    }
}
