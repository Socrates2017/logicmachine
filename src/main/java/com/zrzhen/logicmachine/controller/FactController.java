package com.zrzhen.logicmachine.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.result.ResultGen;
import com.zrzhen.logicmachine.service.FactService;
import com.zrzhen.logicmachine.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fact")
public class FactController {


    @Autowired
    FactService factService;

    /**
     * 获取事实树
     * @param factId
     * @return
     */
    @GetMapping("/tree/{factId}")
    public Result tree(@PathVariable Integer factId) {
        Fact fact = factService.getFactTree(factId);
        return ResultGen.genResult(ResultCode.SUCCESS, fact);
    }

    @PostMapping("/calculate")
    public Result calculate(@RequestBody String json) {

        JsonNode jsonNode = JsonUtil.str2JsonNode(json);




        return ResultGen.genResult(ResultCode.SUCCESS,jsonNode);
    }


}
