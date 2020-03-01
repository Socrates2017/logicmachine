package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.Operator;
import org.apache.ibatis.jdbc.SQL;

public class OperatorSqlProvider {

    public String insertSelective(Operator record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`operator`");
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("`name`", "#{name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Operator record) {
        SQL sql = new SQL();
        sql.UPDATE("`operator`");
        
        if (record.getName() != null) {
            sql.SET("`name` = #{name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("code = #{code,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}