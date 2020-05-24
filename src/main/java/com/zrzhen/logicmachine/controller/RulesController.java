package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.RulesDao;
import com.zrzhen.logicmachine.domain.Rules;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.service.FactService;
import com.zrzhen.logicmachine.service.RulesService;
import com.zrzhen.logicmachine.util.idmaker.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rules")
@RestController
public class RulesController {

    @Autowired
    FactService factService;

    @Autowired
    RulesService rulesService;

    /**
     * @return
     */
    @PutMapping
    public Result put(@RequestBody Rules rules) {
        rules.setId(IdUtil.genId());
        RulesDao.insert(rules);

        return Result.buildSuccess(rules);
    }

    @GetMapping
    public Result get(Long id) {
        Rules rule = rulesService.getTree(id);
        return Result.buildSuccess(rule);
    }

    @GetMapping("/execute")
    public Result execute(Long id, String paramKey) {
        Rules rule = rulesService.getTree(id);
        if (rule != null) {
            rule.setParamKey(paramKey);
            rule.execute();
        }
        return Result.buildSuccess(rule);
    }
}
