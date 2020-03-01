package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.Connective;
import org.apache.ibatis.jdbc.SQL;

public class ConnectiveSqlProvider {

    public String insertSelective(Connective record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`connective`");
        
        if (record.getCode() != null) {
            sql.VALUES("code", "#{code,jdbcType=VARCHAR}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("`name`", "#{name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Connective record) {
        SQL sql = new SQL();
        sql.UPDATE("`connective`");
        
        if (record.getName() != null) {
            sql.SET("`name` = #{name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("code = #{code,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}