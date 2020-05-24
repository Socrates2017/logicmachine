package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.ActionRuleDao;
import com.zrzhen.logicmachine.domain.ActionRule;
import com.zrzhen.logicmachine.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/actionRule")
@RestController
public class ActionRuleController {

    /**
     * @return
     */
    @PutMapping
    public Result put(@RequestBody ActionRule actionRule) {

        ActionRuleDao.insert(actionRule);

        return Result.buildSuccess(actionRule);
    }

    @GetMapping
    public Result get(Long actionId) {
        List<ActionRule> fact = ActionRuleDao.getListByActionId(actionId);
        return Result.buildSuccess(fact);
    }
}
