package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.FactMapper;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.result.ResultGen;
import com.zrzhen.logicmachine.service.FactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {


    private static final Logger log = LoggerFactory.getLogger(FactController.class);

    @Autowired
    FactService factService;

    @Autowired
    FactMapper factMapper;

    @GetMapping("/rootFactList")
    public Result rootFactList() {
        List<Fact> rootFactList = factMapper.factListByType(2);
        return ResultGen.genResult(ResultCode.SUCCESS, rootFactList);
    }
}
