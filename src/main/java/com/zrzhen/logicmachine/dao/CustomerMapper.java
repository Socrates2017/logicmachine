package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface CustomerMapper {
    @Delete({
        "delete from `customer`",
        "where customer_id = #{customerId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer customerId);

    @Insert({
        "insert into `customer` (customer_id, `name`, ",
        "age, sex)",
        "values (#{customerId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{age,jdbcType=INTEGER}, #{sex,jdbcType=VARCHAR})"
    })
    int insert(Customer record);

    @InsertProvider(type=CustomerSqlProvider.class, method="insertSelective")
    int insertSelective(Customer record);

    @Select({
        "select",
        "customer_id, `name`, age, sex",
        "from `customer`",
        "where customer_id = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="customer_id", property="customerId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="age", property="age", jdbcType=JdbcType.INTEGER),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR)
    })
    Customer selectByPrimaryKey(Integer customerId);

    @UpdateProvider(type=CustomerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Customer record);

    @Update({
        "update `customer`",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "age = #{age,jdbcType=INTEGER},",
          "sex = #{sex,jdbcType=VARCHAR}",
        "where customer_id = #{customerId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Customer record);
}