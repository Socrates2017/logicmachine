package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.AtomicFactParam;
import com.zrzhen.logicmachine.domain.AtomicFactParamKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AtomicFactParamMapper {
    @Delete({
        "delete from `atomic_fact_param`",
        "where atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}",
          "and `index` = #{index,jdbcType=SMALLINT}"
    })
    int deleteByPrimaryKey(AtomicFactParamKey key);

    @Insert({
        "insert into `atomic_fact_param` (atomic_fact_id, `index`, ",
        "`name`, `value`, `type`)",
        "values (#{atomicFactId,jdbcType=INTEGER}, #{index,jdbcType=SMALLINT}, ",
        "#{name,jdbcType=VARCHAR}, #{value,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR})"
    })
    int insert(AtomicFactParam record);

    @InsertProvider(type=AtomicFactParamSqlProvider.class, method="insertSelective")
    int insertSelective(AtomicFactParam record);

    @Select({
        "select",
        "atomic_fact_id, `index`, `name`, `value`, `type`",
        "from `atomic_fact_param`",
        "where atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}",
          "and `index` = #{index,jdbcType=SMALLINT}"
    })
    @Results({
        @Result(column="atomic_fact_id", property="atomicFactId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="index", property="index", jdbcType=JdbcType.SMALLINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR)
    })
    AtomicFactParam selectByPrimaryKey(AtomicFactParamKey key);

    @UpdateProvider(type=AtomicFactParamSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AtomicFactParam record);

    @Update({
        "update `atomic_fact_param`",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "`value` = #{value,jdbcType=VARCHAR},",
          "`type` = #{type,jdbcType=VARCHAR}",
        "where atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}",
          "and `index` = #{index,jdbcType=SMALLINT}"
    })
    int updateByPrimaryKey(AtomicFactParam record);
}