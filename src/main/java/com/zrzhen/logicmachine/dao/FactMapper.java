package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.domain.FactConnective;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface FactMapper {


    @Select({
            "select",
            "fact_id, `name`,  atomic_id, `type`",
            "from `fact`",
            "where fact_id = #{factId,jdbcType=INTEGER}"
    })
    @Results(id = "all", value = {
            @Result(column = "fact_id", property = "factId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
            @Result(column = "atomic_id", property = "atomicId", jdbcType = JdbcType.INTEGER),
            @Result(column = "type", property = "type", jdbcType = JdbcType.SMALLINT)
    })
    Fact selectByPrimaryKey(Integer factId);


    @Select({
            "select",
            "fact_id, `name`,  atomic_id, `type`",
            "from `fact`",
            "where type = #{type,jdbcType=INTEGER}"
    })
    @ResultMap("all")
    List<Fact> factListByType(Integer type);
}