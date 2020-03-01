package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.AtomicFact;
import org.apache.ibatis.jdbc.SQL;

public class AtomicFactSqlProvider {

    public String insertSelective(AtomicFact record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`atomic_fact`");
        
        if (record.getAtomicFactId() != null) {
            sql.VALUES("atomic_fact_id", "#{atomicFactId,jdbcType=INTEGER}");
        }
        
        if (record.getTableName() != null) {
            sql.VALUES("`table_name`", "#{tableName,jdbcType=VARCHAR}");
        }
        
        if (record.getColumnName() != null) {
            sql.VALUES("`column_name`", "#{columnName,jdbcType=VARCHAR}");
        }
        
        if (record.getOperator() != null) {
            sql.VALUES("`operator`", "#{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.VALUES("`value`", "#{value,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AtomicFact record) {
        SQL sql = new SQL();
        sql.UPDATE("`atomic_fact`");
        
        if (record.getTableName() != null) {
            sql.SET("`table_name` = #{tableName,jdbcType=VARCHAR}");
        }
        
        if (record.getColumnName() != null) {
            sql.SET("`column_name` = #{columnName,jdbcType=VARCHAR}");
        }
        
        if (record.getOperator() != null) {
            sql.SET("`operator` = #{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.SET("`value` = #{value,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}