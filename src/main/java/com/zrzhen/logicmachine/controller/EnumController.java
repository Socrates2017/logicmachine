package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.constant.ConnectiveEnum;
import com.zrzhen.logicmachine.constant.OperatorEnum;
import com.zrzhen.logicmachine.factfuntion.AtomicFactFunctionEnum;
import com.zrzhen.logicmachine.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/enum")
@RestController
public class EnumController {

    @GetMapping("/operatorList")
    public Result operatorList() {
        List<String> codeList = OperatorEnum.codeList();
        return Result.buildSuccess(codeList);
    }

    @GetMapping("/connectiveList")
    public Result connectiveList() {
        List<String> codeList = ConnectiveEnum.codeList();
        return Result.buildSuccess(codeList);
    }

    @GetMapping("/atomicfactFunList")
    public Result atomicfactFunList() {
        List<String> atomicFactFunctioList = AtomicFactFunctionEnum.nameList();
        List<String> operatorList = OperatorEnum.codeList();

        Map map = new HashMap();
        map.put("atomicFactFunctioList",atomicFactFunctioList);
        map.put("operatorList",operatorList);


        return Result.buildSuccess(map);
    }


}
