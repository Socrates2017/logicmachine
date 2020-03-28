package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.AtomicFact;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AtomicFactMapper {
    @Delete({
        "delete from `atomic_fact`",
        "where atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer atomicFactId);

    @Insert({
        "insert into `atomic_fact` (atomic_fact_id, atomic_fact_function, ",
        "param, `operator`, ",
        "`value`, create_time, ",
        "update_time)",
        "values (#{atomicFactId,jdbcType=INTEGER}, #{atomicFactFunction,jdbcType=VARCHAR}, ",
        "#{param,jdbcType=VARCHAR}, #{operator,jdbcType=VARCHAR}, ",
        "#{value,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(AtomicFact record);

    @InsertProvider(type=AtomicFactSqlProvider.class, method="insertSelective")
    int insertSelective(AtomicFact record);

    @Select({
        "select",
        "atomic_fact_id, atomic_fact_function, param, `operator`, `value`, create_time, ",
        "update_time",
        "from `atomic_fact`",
        "where atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="atomic_fact_id", property="atomicFactId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="atomic_fact_function", property="atomicFactFunction", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AtomicFact selectByPrimaryKey(Integer atomicFactId);

    @UpdateProvider(type=AtomicFactSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AtomicFact record);

    @Update({
        "update `atomic_fact`",
        "set atomic_fact_function = #{atomicFactFunction,jdbcType=VARCHAR},",
          "param = #{param,jdbcType=VARCHAR},",
          "`operator` = #{operator,jdbcType=VARCHAR},",
          "`value` = #{value,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AtomicFact record);
}