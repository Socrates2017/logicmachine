package com.zrzhen.logicmachine.dao;

import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @author chenanlian
 */
public interface GodSqlMapper {

    /**
     * 万能查询接口
     * @param sqlStr
     * @return
     */
    @SelectProvider(type = GodSqlProvider.class, method = "query")
    public List<Map<String, Object>> query(String sqlStr);
}
