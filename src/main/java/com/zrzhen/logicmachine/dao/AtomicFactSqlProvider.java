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
        
        if (record.getAtomicFactFunction() != null) {
            sql.VALUES("atomic_fact_function", "#{atomicFactFunction,jdbcType=VARCHAR}");
        }

        
        if (record.getOperator() != null) {
            sql.VALUES("`operator`", "#{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.VALUES("`value`", "#{value,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AtomicFact record) {
        SQL sql = new SQL();
        sql.UPDATE("`atomic_fact`");
        
        if (record.getAtomicFactFunction() != null) {
            sql.SET("atomic_fact_function = #{atomicFactFunction,jdbcType=VARCHAR}");
        }

        
        if (record.getOperator() != null) {
            sql.SET("`operator` = #{operator,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.SET("`value` = #{value,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}