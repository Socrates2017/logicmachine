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
        "insert into `atomic_fact` (atomic_fact_id, `table_name`, ",
        "`column_name`, `operator`, ",
        "`value`)",
        "values (#{atomicFactId,jdbcType=INTEGER}, #{tableName,jdbcType=VARCHAR}, ",
        "#{columnName,jdbcType=VARCHAR}, #{operator,jdbcType=VARCHAR}, ",
        "#{value,jdbcType=VARCHAR})"
    })
    int insert(AtomicFact record);

    @InsertProvider(type=AtomicFactSqlProvider.class, method="insertSelective")
    int insertSelective(AtomicFact record);

    @Select({
        "select",
        "atomic_fact_id, `table_name`, `column_name`, `operator`, `value`",
        "from `atomic_fact`",
        "where atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="atomic_fact_id", property="atomicFactId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="table_name", property="tableName", jdbcType=JdbcType.VARCHAR),
        @Result(column="column_name", property="columnName", jdbcType=JdbcType.VARCHAR),
        @Result(column="operator", property="operator", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR)
    })
    AtomicFact selectByPrimaryKey(Integer atomicFactId);

    @UpdateProvider(type=AtomicFactSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AtomicFact record);

    @Update({
        "update `atomic_fact`",
        "set `table_name` = #{tableName,jdbcType=VARCHAR},",
          "`column_name` = #{columnName,jdbcType=VARCHAR},",
          "`operator` = #{operator,jdbcType=VARCHAR},",
          "`value` = #{value,jdbcType=VARCHAR}",
        "where atomic_fact_id = #{atomicFactId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AtomicFact record);
}