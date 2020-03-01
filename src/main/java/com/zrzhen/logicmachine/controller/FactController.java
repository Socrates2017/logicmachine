package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.result.ResultGen;
import com.zrzhen.logicmachine.service.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fact")
public class FactController {


    @Autowired
    FactService factService;

    @GetMapping("/test/{factId}")
    public Result test(@PathVariable Integer factId) {

        Fact fact = factService.getFactTree(factId);

        return ResultGen.genResult(ResultCode.SUCCESS, fact);
    }

}
