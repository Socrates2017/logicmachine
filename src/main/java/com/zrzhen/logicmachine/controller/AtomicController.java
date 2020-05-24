package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.AtomicFactDao;
import com.zrzhen.logicmachine.domain.AtomicFact;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.result.ResultCode;
import com.zrzhen.logicmachine.util.idmaker.IdUtil;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/atomicfact")
@RestController
public class AtomicController {

    /**
     * @return
     */
    @PutMapping
    public Result put(@RequestBody @Valid AtomicFact fact) {

        int count = AtomicFactDao.countByValue(fact);
        if (count < 1) {
            fact.setId(IdUtil.genId());
            AtomicFactDao.insert(fact);
            return Result.buildSuccess(fact);
        } else {
            return Result.build(ResultCode.ATOMIC_FACT_EXIST);
        }

    }

    @GetMapping
    public Result get(Long id) {
        AtomicFact fact = AtomicFactDao.getById(id);
        return Result.buildSuccess(fact);
    }

    @GetMapping("/all")
    public Result all() {
        List<AtomicFact> fact = AtomicFactDao.all();
        return Result.buildSuccess(fact);
    }
}
