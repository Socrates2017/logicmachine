package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.FactDao;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.domain.FactManager;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.service.FactService;
import com.zrzhen.logicmachine.util.JsonUtil;
import com.zrzhen.logicmachine.util.idmaker.IdUtil;
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
     * @return
     */
    @PutMapping
    public Result put(@RequestBody Fact fact) {

        if (fact.getType() == 0) {
            int count = FactDao.countByActomicId(fact.getAtomicId());
            if (count > 0) {
                return Result.build(ResultCode.FACT_EXIST);
            }
        }
        fact.setId(IdUtil.genId());
        FactDao.insert(fact);

        return Result.buildSuccess(fact);
    }

    @GetMapping
    public Result get(Long id) {
        Fact fact = FactDao.getById(id);
        return Result.buildSuccess(fact);
    }


    /**
     * 获取事实树
     *
     * @param factId
     * @return
     */
    @GetMapping("/tree/{factId}")
    public Result tree(@PathVariable Long factId) {
        Fact fact = factService.getFactTree(factId);
        return Result.buildSuccess(fact);
    }

    /**
     * 输入原子事实已被赋值的事实树，计算根事实的真值
     *
     * @param json
     * @return
     */
    @PostMapping("/calculate")
    public Result calculate(@RequestBody String json) {
        Fact fact = JsonUtil.str2Entity(json, Fact.class);
        new FactManager(fact, "dd").calculateRoot();
        return Result.buildSuccess(fact);
    }


}
