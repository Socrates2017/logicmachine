package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.AtomicFactDao;
import com.zrzhen.logicmachine.domain.AtomicFact;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.util.idmaker.IdUtil;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/factconnective")
@RestController
public class FactConnectiveController {

    /**
     * @return
     */
    @PutMapping
    public Result put(@RequestBody AtomicFact fact) {
        fact.setId(IdUtil.genId());
        AtomicFactDao.insert(fact);

        return Result.buildSuccess(fact);
    }

    @GetMapping
    public Result get(Long id) {
        AtomicFact fact = AtomicFactDao.getById(id);
        return Result.buildSuccess(fact);
    }
}
