package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.domain.Action;
import com.zrzhen.logicmachine.domain.FactConnective;
import com.zrzhen.logicmachine.domain.Rule;
import com.zrzhen.logicmachine.zatis.DbSource;
import com.zrzhen.logicmachine.zatis.Orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleDao {

    private static String table = "rule";

    private static Orm<Rule> orm = new Orm(Rule.class);

    private static DbSource db = DbEnum.RULE.getDb();

    public static int insert(Rule record) {

        Map<String, Object> valueMap = new HashMap<>();

        if (record.getId() != null) {
            valueMap.put("id", record.getId());
        }
        if (record.getFactId()!= null) {
            valueMap.put("fact_id", record.getFactId());
        }
        if (record.getActionId() != null) {
            valueMap.put("action_id", record.getActionId());
        }
        return db.insertAutocommit(table, valueMap);
    }

    public static Rule getById(Long id) {

        String sql = "select * from " + table + " where id =?";
        Object[] bindArgs = new Object[]{id};
        Map<String, Object> result = db.getOne(sql, bindArgs);

        Rule record = orm.getEntity(result);
        return record;
    }

    public static List<Rule>  getActionRuleByActionId(Long actionId) {

        String sql = "select\n" +
                "\tr.id ,r.fact_id ,r.action_id \n" +
                "from\n" +
                "\trule r\n" +
                "left join action_rule ar on\n" +
                "\tr.id = ar.rule_id\n" +
                "where\n" +
                "\tar.action_id = ?";
        Object[] bindArgs = new Object[]{actionId};

        List<Map<String, Object>> result = db.getList(sql, bindArgs);

        List<Rule> record = orm.getEntityList(result);
        return record;
    }

    public static List<Rule>  getRulesRuleByRulesId(Long rulesId) {

        String sql = "select\n" +
                "\tr.id ,r.fact_id ,r.action_id \n" +
                "from\n" +
                "\trule r\n" +
                "left join rules_rule ar on\n" +
                "\tr.id = ar.rule_id\n" +
                "where\n" +
                "\tar.rules_id = ?";
        Object[] bindArgs = new Object[]{rulesId};

        List<Map<String, Object>> result = db.getList(sql, bindArgs);

        List<Rule> record = orm.getEntityList(result);
        return record;
    }
}
