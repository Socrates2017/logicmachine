package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.Operator;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface OperatorMapper {
    @Delete({
        "delete from `operator`",
        "where code = #{code,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String code);

    @Insert({
        "insert into `operator` (code, `name`, ",
        "create_time, update_time)",
        "values (#{code,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Operator record);

    @InsertProvider(type=OperatorSqlProvider.class, method="insertSelective")
    int insertSelective(Operator record);

    @Select({
        "select",
        "code, `name`, create_time, update_time",
        "from `operator`",
        "where code = #{code,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Operator selectByPrimaryKey(String code);

    @UpdateProvider(type=OperatorSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Operator record);

    @Update({
        "update `operator`",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where code = #{code,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Operator record);
}