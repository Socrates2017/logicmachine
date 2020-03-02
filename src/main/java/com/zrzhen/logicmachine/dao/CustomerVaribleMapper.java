package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.CustomerVarible;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CustomerVaribleMapper {
    @Delete({
        "delete from `customer_varible`",
        "where customer_varible_id = #{customerVaribleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer customerVaribleId);

    @Insert({
        "insert into `customer_varible` (customer_varible_id, call_time_long_sum_last_week, ",
        "create_time, update_time)",
        "values (#{customerVaribleId,jdbcType=INTEGER}, #{callTimeLongSumLastWeek,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(CustomerVarible record);

    @InsertProvider(type=CustomerVaribleSqlProvider.class, method="insertSelective")
    int insertSelective(CustomerVarible record);

    @Select({
        "select",
        "customer_varible_id, call_time_long_sum_last_week, create_time, update_time",
        "from `customer_varible`",
        "where customer_varible_id = #{customerVaribleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="customer_varible_id", property="customerVaribleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="call_time_long_sum_last_week", property="callTimeLongSumLastWeek", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    CustomerVarible selectByPrimaryKey(Integer customerVaribleId);

    @UpdateProvider(type=CustomerVaribleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CustomerVarible record);

    @Update({
        "update `customer_varible`",
        "set call_time_long_sum_last_week = #{callTimeLongSumLastWeek,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where customer_varible_id = #{customerVaribleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CustomerVarible record);
}