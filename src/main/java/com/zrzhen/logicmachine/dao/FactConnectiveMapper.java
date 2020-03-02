package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.FactConnective;
import com.zrzhen.logicmachine.domain.FactConnectiveKey;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FactConnectiveMapper {
    @Delete({
        "delete from `fact_connective`",
        "where parent_fact = #{parentFact,jdbcType=INTEGER}",
          "and child_fact = #{childFact,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(FactConnectiveKey key);

    @Insert({
        "insert into `fact_connective` (parent_fact, child_fact, ",
        "connective, create_time, ",
        "update_time)",
        "values (#{parentFact,jdbcType=INTEGER}, #{childFact,jdbcType=INTEGER}, ",
        "#{connective,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(FactConnective record);

    @InsertProvider(type=FactConnectiveSqlProvider.class, method="insertSelective")
    int insertSelective(FactConnective record);

    @Select({
        "select",
        "parent_fact, child_fact, connective, create_time, update_time",
        "from `fact_connective`",
        "where parent_fact = #{parentFact,jdbcType=INTEGER}",
          "and child_fact = #{childFact,jdbcType=INTEGER}"
    })
    @Results(id = "all", value = {
        @Result(column="parent_fact", property="parentFact", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="child_fact", property="childFact", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="connective", property="connective", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FactConnective selectByPrimaryKey(FactConnectiveKey key);

    @UpdateProvider(type=FactConnectiveSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FactConnective record);

    @Update({
        "update `fact_connective`",
        "set connective = #{connective,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where parent_fact = #{parentFact,jdbcType=INTEGER}",
          "and child_fact = #{childFact,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FactConnective record);





    @Select({
            "select",
            "parent_fact, child_fact, connective",
            "from `fact_connective`",
            "where parent_fact = #{parentFact,jdbcType=INTEGER}"
    })
    @ResultMap("all")
    List<FactConnective> selectByParentFact(int parentFact);
}