package com.zrzhen.logicmachine.service.imp;


import com.zrzhen.logicmachine.dao.FactConnectiveMapper;
import com.zrzhen.logicmachine.dao.FactMapper;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.domain.FactConnective;
import com.zrzhen.logicmachine.service.FactService;
import com.zrzhen.logicmachine.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenanlian
 */
@Service
public class FactServiceImpl implements FactService {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    @Autowired
    FactConnectiveMapper factConnectiveMapper;

    @Autowired
    FactMapper factMapper;

    @Override
    public Fact getFactTree(Integer factId) {
        Fact fact = factMapper.selectByPrimaryKey(factId);
        getChildFactList(fact);
        return fact;
    }

    /**
     * 获取所有子节点
     * @param fact
     */
    public void getChildFactList(Fact fact){
        if (fact.getAtomicId()==0){
            List<Fact> childFacts = new ArrayList<>();
            List<FactConnective> factConnectiveList = factConnectiveMapper.selectByParentFact(fact.getFactId());
            if (factConnectiveList != null && factConnectiveList.size() > 0) {
                fact.setChildFactsConnective(factConnectiveList.get(0).getConnective());
                for (int i=0;i<factConnectiveList.size();i++){
                    FactConnective factConnective = factConnectiveList.get(i);
                    Fact childFact = factMapper.selectByPrimaryKey(factConnective.getChildFact());
                    getChildFactList(childFact);
                    childFacts.add(childFact);
                }
            }
            fact.setChildFacts(childFacts);
        }
    }
}
