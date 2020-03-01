package com.zrzhen.logicmachine.dao;

import com.zrzhen.logicmachine.domain.Connective;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ConnectiveMapper {
    @Delete({
        "delete from `connective`",
        "where code = #{code,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String code);

    @Insert({
        "insert into `connective` (code, `name`)",
        "values (#{code,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR})"
    })
    int insert(Connective record);

    @InsertProvider(type=ConnectiveSqlProvider.class, method="insertSelective")
    int insertSelective(Connective record);

    @Select({
        "select",
        "code, `name`",
        "from `connective`",
        "where code = #{code,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Connective selectByPrimaryKey(String code);

    @UpdateProvider(type=ConnectiveSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Connective record);

    @Update({
        "update `connective`",
        "set `name` = #{name,jdbcType=VARCHAR}",
        "where code = #{code,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Connective record);
}