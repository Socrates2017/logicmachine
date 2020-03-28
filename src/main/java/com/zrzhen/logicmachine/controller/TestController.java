package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.FactMapper;
import com.zrzhen.logicmachine.db.DbEnum;
import com.zrzhen.logicmachine.db.DbSource;
import com.zrzhen.logicmachine.db.DbUtil;
import com.zrzhen.logicmachine.db.SqlNotFormatException;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.result.ResultGen;
import com.zrzhen.logicmachine.service.FactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {


    private static final Logger log = LoggerFactory.getLogger(FactController.class);

    @Autowired
    FactService factService;

    @Autowired
    FactMapper factMapper;

    @GetMapping("/query")
    public Result query() {

        DbSource ruleDb = DbEnum.RULE.getDb();

        String sql = "SELECT\n" +
                "\t*\n" +
                "FROM\n" +
                "\tatomic_fact\n" +
                "LIMIT ?;";
        Object[] bindArgs = new Object[]{5};
        ruleDb.setSql(sql);
        ruleDb.setBindArgs(bindArgs);
        List<Map<String, Object>> result = DbUtil.query(ruleDb);
        return ResultGen.genResult(ResultCode.SUCCESS, result);
    }

    @GetMapping("/update")
    public Result update() {

        DbSource ruleDb = DbEnum.RULE.getDb();

        String sql = "UPDATE atomic_fact\n" +
                "SET `value` = ?\n" +
                "WHERE\n" +
                "\tatomic_fact_id = ?";
        Object[] bindArgs = new Object[]{2, 1};
        ruleDb.setSql(sql);
        ruleDb.setBindArgs(bindArgs);
        Integer result = null;
        try {
            result = DbUtil.operate(ruleDb);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (SqlNotFormatException e) {
            e.printStackTrace();
        }
        try {
            DbUtil.commit(ruleDb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ResultGen.genResult(ResultCode.SUCCESS, result);
    }
}
