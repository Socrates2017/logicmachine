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
        "connective)",
        "values (#{parentFact,jdbcType=INTEGER}, #{childFact,jdbcType=INTEGER}, ",
        "#{connective,jdbcType=VARCHAR})"
    })
    int insert(FactConnective record);

    @InsertProvider(type=FactConnectiveSqlProvider.class, method="insertSelective")
    int insertSelective(FactConnective record);

    @Select({
        "select",
        "parent_fact, child_fact, connective",
        "from `fact_connective`",
        "where parent_fact = #{parentFact,jdbcType=INTEGER}",
          "and child_fact = #{childFact,jdbcType=INTEGER}"
    })
    @Results(id = "all", value = {
        @Result(column="parent_fact", property="parentFact", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="child_fact", property="childFact", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="connective", property="connective", jdbcType=JdbcType.VARCHAR)
    })
    FactConnective selectByPrimaryKey(FactConnectiveKey key);

    @UpdateProvider(type=FactConnectiveSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FactConnective record);

    @Update({
        "update `fact_connective`",
        "set connective = #{connective,jdbcType=VARCHAR}",
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