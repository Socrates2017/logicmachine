package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.Fact;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface FactMapper {
    @Delete({
        "delete from `fact`",
        "where fact_id = #{factId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer factId);

    @Insert({
        "insert into `fact` (fact_id, atomic_id)",
        "values (#{factId,jdbcType=INTEGER}, #{atomicId,jdbcType=INTEGER})"
    })
    int insert(Fact record);

    @InsertProvider(type=FactSqlProvider.class, method="insertSelective")
    int insertSelective(Fact record);

    @Select({
        "select",
        "fact_id, atomic_id",
        "from `fact`",
        "where fact_id = #{factId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="fact_id", property="factId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="atomic_id", property="atomicId", jdbcType=JdbcType.INTEGER)
    })
    Fact selectByPrimaryKey(Integer factId);

    @UpdateProvider(type=FactSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Fact record);

    @Update({
        "update `fact`",
        "set atomic_id = #{atomicId,jdbcType=INTEGER}",
        "where fact_id = #{factId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Fact record);
}