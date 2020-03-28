package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.AtomicFactParam;
import org.apache.ibatis.jdbc.SQL;

public class AtomicFactParamSqlProvider {

    public String insertSelective(AtomicFactParam record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`atomic_fact_param`");
        
        if (record.getAtomicFactId() != null) {
            sql.VALUES("atomic_fact_id", "#{atomicFactId,jdbcType=INTEGER}");
        }
        
        if (record.getIndex() != null) {
            sql.VALUES("`index`", "#{index,jdbcType=SMALLINT}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("`name`", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.VALUES("`value`", "#{value,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("`type`", "#{type,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AtomicFactParam record) {
        SQL sql = new SQL();
        sql.UPDATE("`atomic_fact_param`");
        
        if (record.getName() != null) {
            sql.SET("`name` = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getValue() != null) {
            sql.SET("`value` = #{value,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("`type` = #{type,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}");
        sql.WHERE("`index` = #{index,jdbcType=SMALLINT}");
        
        return sql.toString();
    }
}