package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.RuleDao;
import com.zrzhen.logicmachine.domain.Action;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.domain.Rule;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.service.ActionService;
import com.zrzhen.logicmachine.service.FactService;
import com.zrzhen.logicmachine.util.idmaker.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rule")
@RestController
public class RuleController {

    @Autowired
    FactService factService;

    @Autowired
    ActionService actionService;

    /**
     * @return
     */
    @PutMapping
    public Result put(@RequestBody Rule rule) {
        rule.setId(IdUtil.genId());
        RuleDao.insert(rule);

        return Result.buildSuccess(rule);
    }

    @GetMapping
    public Result get(Long id) {
        Rule rule = RuleDao.getById(id);

        if (rule != null) {
            Long factId = rule.getFactId();
            Long actionId = rule.getActionId();

            Fact fact = factService.getFactTree(factId);

            Action action = actionService.getTree(actionId);

            rule.setFact(fact);
            rule.setAction(action);
        }
        return Result.buildSuccess(rule);
    }
}
