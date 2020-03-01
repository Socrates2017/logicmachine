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
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Fact record) {
        SQL sql = new SQL();
        sql.UPDATE("`fact`");
        
        if (record.getAtomicId() != null) {
            sql.SET("atomic_id = #{atomicId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("fact_id = #{factId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}