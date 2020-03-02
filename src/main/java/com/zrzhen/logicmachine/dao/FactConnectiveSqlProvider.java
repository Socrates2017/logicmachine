package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.FactConnective;
import org.apache.ibatis.jdbc.SQL;

public class FactConnectiveSqlProvider {

    public String insertSelective(FactConnective record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`fact_connective`");
        
        if (record.getParentFact() != null) {
            sql.VALUES("parent_fact", "#{parentFact,jdbcType=INTEGER}");
        }
        
        if (record.getChildFact() != null) {
            sql.VALUES("child_fact", "#{childFact,jdbcType=INTEGER}");
        }
        
        if (record.getConnective() != null) {
            sql.VALUES("connective", "#{connective,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FactConnective record) {
        SQL sql = new SQL();
        sql.UPDATE("`fact_connective`");
        
        if (record.getConnective() != null) {
            sql.SET("connective = #{connective,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("parent_fact = #{parentFact,jdbcType=INTEGER}");
        sql.WHERE("child_fact = #{childFact,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}