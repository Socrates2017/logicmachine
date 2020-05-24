package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.RulesRule;
import com.zrzhen.logicmachine.zatis.DbSource;
import com.zrzhen.logicmachine.zatis.Orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RulesRuleDao {

    private static String table = "rules_rule";

    private static Orm<RulesRule> orm = new Orm(RulesRule.class);

    private static DbSource db = DbEnum.RULE.getDb();

    public static int insert(RulesRule record) {

        Map<String, Object> valueMap = new HashMap<>();

        if (record.getRulesId() != null) {
            valueMap.put("rules_id", record.getRulesId());
        }
        if (record.getRuleId() != null) {
            valueMap.put("rule_id", record.getRuleId());
        }
        return db.insertAutocommit(table, valueMap);
    }

    public static RulesRule getByRulesIdAndRuleId(Long rulesId, Long ruleId) {

        String sql = "select * from " + table + " where rules_id =? and rule_id =?";
        Object[] bindArgs = new Object[]{rulesId, ruleId};
        Map<String, Object> result = db.getOne(sql, bindArgs);

        RulesRule record = orm.getEntity(result);
        return record;
    }

    public static List<RulesRule> getListByRulesId(Long rulesId) {

        String sql = "select * from " + table + " where rules_id =?";
        Object[] bindArgs = new Object[]{rulesId};
        List<Map<String, Object>> result = db.getList(sql, bindArgs);

        List<RulesRule> record = orm.getEntityList(result);
        return record;
    }
}
