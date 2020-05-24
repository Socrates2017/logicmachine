package com.zrzhen.logicmachine.controller;

import com.zrzhen.logicmachine.dao.ActionDao;
import com.zrzhen.logicmachine.domain.Action;
import com.zrzhen.logicmachine.result.Result;
import com.zrzhen.logicmachine.service.ActionService;
import com.zrzhen.logicmachine.util.idmaker.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/action")
@RestController
public class ActionController {

    @Autowired
    ActionService actionService;

    /**
     * @return
     */
    @PutMapping
    public Result put(@RequestBody Action then) {
        then.setId(IdUtil.genId());
        ActionDao.insert(then);

        return Result.buildSuccess(then);
    }

    @GetMapping
    public Result get(Long id) {
        Action action = actionService.getTree(id);
        return Result.buildSuccess(action);
    }
}
