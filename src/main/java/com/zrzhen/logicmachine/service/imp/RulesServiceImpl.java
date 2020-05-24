package com.zrzhen.logicmachine.service.imp;


import com.zrzhen.logicmachine.dao.RuleDao;
import com.zrzhen.logicmachine.dao.RulesDao;
import com.zrzhen.logicmachine.domain.Action;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.domain.Rule;
import com.zrzhen.logicmachine.domain.Rules;
import com.zrzhen.logicmachine.service.ActionService;
import com.zrzhen.logicmachine.service.FactService;
import com.zrzhen.logicmachine.service.RulesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenanlian
 */
@Service
public class RulesServiceImpl implements RulesService {

    private static final Logger log = LoggerFactory.getLogger(RulesServiceImpl.class);



    @Autowired
    FactService factService;

    @Autowired
    ActionService actionService;

    @Override
    public Rules getTree(Long id) {
        Rules rules = RulesDao.getById(id);

        List<Rule> ruleList = RuleDao.getRulesRuleByRulesId(id);

        if (ruleList != null) {
            for (Rule rule : ruleList) {
                Long actionId = rule.getActionId();
                Long factId = rule.getFactId();
                Action action1 = actionService.getTree(actionId);
                Fact fact = factService.getFactTree(factId);
                rule.setAction(action1);
                rule.setFact(fact);
            }
        }
        rules.setRuleList(ruleList);

        return rules;
    }
}
