package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.result.ResultGen;
import com.zrzhen.logicmachine.service.FactService;
import com.zrzhen.logicmachine.util.FactUtil;
import com.zrzhen.logicmachine.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fact")
public class FactController {


    private static final Logger log = LoggerFactory.getLogger(FactController.class);

    @Autowired
    FactService factService;

    /**
     * 获取事实树
     *
     * @param factId
     * @return
     */
    @GetMapping("/tree/{factId}")
    public Result tree(@PathVariable Integer factId) {
        Fact fact = factService.getFactTree(factId);
        return ResultGen.genResult(ResultCode.SUCCESS, fact);
    }

    /**
     * 获取事实树并对原子事实进行赋值
     * @param factId
     * @param customerId
     * @return
     */
    @GetMapping("/treeSetAtomicValue/{factId}")
    public Result treeSetAtomicValue(@PathVariable Integer factId, @RequestParam Integer customerId) {
        Fact fact = factService.getFactTreeAndSetAtomicValue(factId, customerId);
        return ResultGen.genResult(ResultCode.SUCCESS, fact);
    }

    /**
     * 输入原子事实已被赋值的事实树，计算根事实的真值
     * @param json
     * @return
     */
    @PostMapping("/calculate")
    public Result calculate(@RequestBody String json) {
        Fact fact = JsonUtil.str2Entity(json, Fact.class);
        FactUtil.calculateRoot(fact);
        return ResultGen.genResult(ResultCode.SUCCESS, fact);
    }


}
