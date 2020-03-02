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
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Connective record) {
        SQL sql = new SQL();
        sql.UPDATE("`connective`");
        
        if (record.getName() != null) {
            sql.SET("`name` = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("code = #{code,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}