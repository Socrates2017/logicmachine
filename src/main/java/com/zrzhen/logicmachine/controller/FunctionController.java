package com.zrzhen.logicmachine.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.zrzhen.logicmachine.dao.FunctionMapper;
import com.zrzhen.logicmachine.domain.Function;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.result.ResultGen;
import com.zrzhen.logicmachine.util.FunctionUtil;
import com.zrzhen.logicmachine.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author chenanlian
 */
@RestController
@RequestMapping("/funtion")
public class FunctionController {

    @Autowired
    FunctionMapper functionMapper;

    @Autowired
    FunctionUtil functionUtil;

    @PostMapping("/test")
    public Result test(HttpServletRequest request) {
        JsonNode jsonNode = JsonUtil.request2JsonNode(request);
        String name = jsonNode.get("name").asText();
        Integer customer_id = jsonNode.get("customer_id").asInt();
        JsonNode param = jsonNode.get("param");

        Function function = functionMapper.selectByName(name);
        String path = function.getPath();

        String[] paths =path.split("#");
        Object result =null;
        try {
            Class<?> threadClazz =  Class.forName(paths[0]);
            Method method = threadClazz.getMethod(paths[1], String.class,int.class,int.class);
            String variable = param.get("variable").asText();
            int num = param.get("num").asInt();
            Object[] paramValues ={variable,num,customer_id};
            result = method.invoke(functionUtil, paramValues);

        } catch (Exception e) {
            e.printStackTrace();
        }




        return ResultGen.genResult(ResultCode.SUCCESS, result);
    }


}
