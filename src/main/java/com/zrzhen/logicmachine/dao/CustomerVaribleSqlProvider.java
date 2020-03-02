package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.CustomerVarible;
import org.apache.ibatis.jdbc.SQL;

public class CustomerVaribleSqlProvider {

    public String insertSelective(CustomerVarible record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("`customer_varible`");
        
        if (record.getCustomerVaribleId() != null) {
            sql.VALUES("customer_varible_id", "#{customerVaribleId,jdbcType=INTEGER}");
        }
        
        if (record.getCallTimeLongSumLastWeek() != null) {
            sql.VALUES("call_time_long_sum_last_week", "#{callTimeLongSumLastWeek,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CustomerVarible record) {
        SQL sql = new SQL();
        sql.UPDATE("`customer_varible`");
        
        if (record.getCallTimeLongSumLastWeek() != null) {
            sql.SET("call_time_long_sum_last_week = #{callTimeLongSumLastWeek,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("customer_varible_id = #{customerVaribleId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}