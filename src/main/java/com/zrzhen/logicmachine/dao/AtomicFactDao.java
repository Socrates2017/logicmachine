package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.AtomicFact;
import com.zrzhen.logicmachine.zatis.DbSource;
import com.zrzhen.logicmachine.zatis.Orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AtomicFactDao {

    private static String table = "atomic_fact";

    private static Orm<AtomicFact> orm = new Orm(AtomicFact.class);

    private static DbSource db = DbEnum.RULE.getDb();

    public static int insert(AtomicFact record) {

        Map<String, Object> valueMap = new HashMap<>();

        if (record.getId() != null) {
            valueMap.put("id", record.getId());
        }
        if (record.getAtomicFactFunction() != null) {
            valueMap.put("atomic_fact_function", record.getAtomicFactFunction());
        }
        if (record.getOperator() != null) {
            valueMap.put("operator", record.getOperator());
        }
        if (record.getValue() != null) {
            valueMap.put("value", record.getValue());
        }

        return db.insertAutocommit(table, valueMap);
    }

    public static AtomicFact getById(Long id) {

        String sql = "select * from " + table + " where id =?";
        Object[] bindArgs = new Object[]{id};
        Map<String, Object> result = db.getOne(sql, bindArgs);

        AtomicFact atomicFact = orm.getEntity(result);
        return atomicFact;
    }

    public static int countByValue(AtomicFact atomicFact) {

        String sql = "select count(1) from " + table + " where atomic_fact_function =? and operator=? and value=?";
        Object[] bindArgs = new Object[]{atomicFact.getAtomicFactFunction(), atomicFact.getOperator(), atomicFact.getValue()};
        int result = db.count(sql, bindArgs);
        return result;
    }

    public static List<AtomicFact> all() {

        String sql = "select * from " + table;
        List<Map<String, Object>> result = db.getList(sql, null);

        List<AtomicFact> atomicFact = orm.getEntityList(result);
        return atomicFact;
    }
}
