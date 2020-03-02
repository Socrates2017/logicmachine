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
        "insert into `fact` (fact_id, atomic_id, ",
        "`type`, create_time, ",
        "update_time)",
        "values (#{factId,jdbcType=INTEGER}, #{atomicId,jdbcType=INTEGER}, ",
        "#{type,jdbcType=SMALLINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Fact record);

    @InsertProvider(type=FactSqlProvider.class, method="insertSelective")
    int insertSelective(Fact record);

    @Select({
        "select",
        "fact_id, atomic_id, `type`, create_time, update_time",
        "from `fact`",
        "where fact_id = #{factId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="fact_id", property="factId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="atomic_id", property="atomicId", jdbcType=JdbcType.INTEGER),
        @Result(column="type", property="type", jdbcType=JdbcType.SMALLINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Fact selectByPrimaryKey(Integer factId);

    @UpdateProvider(type=FactSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Fact record);

    @Update({
        "update `fact`",
        "set atomic_id = #{atomicId,jdbcType=INTEGER},",
          "`type` = #{type,jdbcType=SMALLINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where fact_id = #{factId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Fact record);
}