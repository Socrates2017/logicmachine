package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.RulesRuleDao;
import com.zrzhen.logicmachine.domain.RulesRule;
import com.zrzhen.logicmachine.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/rulesRule")
@RestController
public class RulesRuleController {

    /**
     * @return
     */
    @PutMapping
    public Result put(@RequestBody RulesRule actionRule) {

        RulesRuleDao.insert(actionRule);

        return Result.buildSuccess(actionRule);
    }

    @GetMapping
    public Result get(Long rulesId) {
        List<RulesRule> fact = RulesRuleDao.getListByRulesId(rulesId);
        return Result.buildSuccess(fact);
    }
}
