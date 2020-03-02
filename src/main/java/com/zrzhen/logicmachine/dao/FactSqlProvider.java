package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.Fact;
import org.apache.ibatis.jdbc.SQL;

public class FactSqlProvider {

    public String insertSelective(Fact record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`fact`");
        
        if (record.getFactId() != null) {
            sql.VALUES("fact_id", "#{factId,jdbcType=INTEGER}");
        }
        
        if (record.getAtomicId() != null) {
            sql.VALUES("atomic_id", "#{atomicId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("`type`", "#{type,jdbcType=SMALLINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Fact record) {
        SQL sql = new SQL();
        sql.UPDATE("`fact`");
        
        if (record.getAtomicId() != null) {
            sql.SET("atomic_id = #{atomicId,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("`type` = #{type,jdbcType=SMALLINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("fact_id = #{factId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}