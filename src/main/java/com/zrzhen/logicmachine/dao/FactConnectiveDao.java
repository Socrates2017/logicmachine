package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.FactConnective;
import com.zrzhen.logicmachine.zatis.DbSource;
import com.zrzhen.logicmachine.zatis.Orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactConnectiveDao {

    private static String table = "fact_connective";

    private static Orm<FactConnective> orm = new Orm(FactConnective.class);

    private static DbSource db = DbEnum.RULE.getDb();

    public static int insert(FactConnective record) {

        Map<String, Object> valueMap = new HashMap<>();

        if (record.getParentFact() != null) {
            valueMap.put("parent_fact", record.getParentFact());
        }
        if (record.getChildFact() != null) {
            valueMap.put("child_fact", record.getChildFact());
        }
        if (record.getConnective() != null) {
            valueMap.put("connective", record.getConnective());
        }
        return db.insertAutocommit(table, valueMap);
    }

    public static FactConnective getById(Long id) {

        String sql = "select * from " + table + " where id =?";
        Object[] bindArgs = new Object[]{id};
        Map<String, Object> result = db.getOne(sql, bindArgs);

        FactConnective record = orm.getEntity(result);
        return record;
    }

    public static List<FactConnective> getListByParentFact(Long parentFact) {

        String sql = "select * from " + table + " where parent_fact =?";
        Object[] bindArgs = new Object[]{parentFact};
        List<Map<String, Object>> result = db.getList(sql, bindArgs);

        List<FactConnective> record = orm.getEntityList(result);
        return record;
    }
}
