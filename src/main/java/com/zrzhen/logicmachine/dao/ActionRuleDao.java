package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.ActionRule;
import com.zrzhen.logicmachine.domain.FactConnective;
import com.zrzhen.logicmachine.zatis.DbSource;
import com.zrzhen.logicmachine.zatis.Orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionRuleDao {

    private static String table = "action_rule";

    private static Orm<ActionRule> orm = new Orm(ActionRule.class);

    private static DbSource db = DbEnum.RULE.getDb();

    public static int insert(ActionRule record) {

        Map<String, Object> valueMap = new HashMap<>();

        if (record.getActionId() != null) {
            valueMap.put("action_id", record.getActionId());
        }
        if (record.getRuleId() != null) {
            valueMap.put("rule_id", record.getRuleId());
        }
        return db.insertAutocommit(table, valueMap);
    }

    public static ActionRule getByActionIdAndRuleId(Long actionId, Long ruleId) {

        String sql = "select * from " + table + " where action_id =? and rule_id =?";
        Object[] bindArgs = new Object[]{actionId, ruleId};
        Map<String, Object> result = db.getOne(sql, bindArgs);

        ActionRule record = orm.getEntity(result);
        return record;
    }

    public static List<ActionRule> getListByActionId(Long actionId) {

        String sql = "select * from " + table + " where action_id =?";
        Object[] bindArgs = new Object[]{actionId};
        List<Map<String, Object>> result = db.getList(sql, bindArgs);

        List<ActionRule> record = orm.getEntityList(result);
        return record;
    }
}
