package com.zrzhen.logicmachine.service.imp;


import com.zrzhen.logicmachine.dao.ActionDao;
import com.zrzhen.logicmachine.dao.RuleDao;
import com.zrzhen.logicmachine.domain.Action;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.domain.Rule;
import com.zrzhen.logicmachine.service.ActionService;
import com.zrzhen.logicmachine.service.FactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenanlian
 */
@Service
public class ActionServiceImpl implements ActionService {

    private static final Logger log = LoggerFactory.getLogger(ActionServiceImpl.class);


    @Autowired
    FactService factService;

    @Override
    public Action getTree(Long id) {
        Action action = ActionDao.getById(id);

        //如果执行的是规则集，则获取规则集信息
        if (action.getType() != 0) {
            List<Rule> ruleList = RuleDao.getActionRuleByActionId(id);

            if (ruleList != null) {
                for (Rule rule : ruleList) {
                    Long actionId = rule.getActionId();
                    Long factId = rule.getFactId();
                    Action action1 = getTree(actionId);
                    Fact fact = factService.getFactTree(factId);
                    rule.setAction(action1);
                    rule.setFact(fact);
                }
            }
            action.setRuleList(ruleList);
        }
        return action;
    }
}
