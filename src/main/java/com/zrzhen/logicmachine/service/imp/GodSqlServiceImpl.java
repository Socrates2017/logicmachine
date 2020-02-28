package com.zrzhen.logicmachine.service.imp;


import com.zrzhen.logicmachine.dao.GodSqlMapper;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.result.ResultGen;
import com.zrzhen.logicmachine.service.GodSqlService;
import com.zrzhen.logicmachine.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author chenanlian
 */
@Service
public class GodSqlServiceImpl implements GodSqlService {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);


    @Autowired
    GodSqlMapper godSqlMapper;


    @Override
    public Result query(String sqlStr) {
        Result result = null;
        if (sqlStr.startsWith("select top")) {
            log.info("万能查询：{}", sqlStr);
            try {
                List<Map<String, Object>> res = godSqlMapper.query(sqlStr);
                result = ResultGen.genResult(ResultCode.SUCCESS, res);
            } catch (Exception e) {
                log.error("万能查询失败", e);
                result = ResultGen.genResult(ResultCode.FAIL, "万能查询失败");
            }

        } else {
            result = ResultGen.genResult(ResultCode.FAIL, "危险sql");
        }

        return result;
    }
}
