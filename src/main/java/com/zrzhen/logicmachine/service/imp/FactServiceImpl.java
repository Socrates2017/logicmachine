package com.zrzhen.logicmachine.service.imp;


import com.zrzhen.logicmachine.dao.FactConnectiveDao;
import com.zrzhen.logicmachine.dao.FactDao;
import com.zrzhen.logicmachine.domain.Fact;
import com.zrzhen.logicmachine.domain.FactConnective;
import com.zrzhen.logicmachine.service.FactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenanlian
 */
@Service
public class FactServiceImpl implements FactService {

    private static final Logger log = LoggerFactory.getLogger(FactServiceImpl.class);


    @Override
    public Fact getFactTree(Long id) {
        Fact fact = FactDao.getById(id);
        getChildFactList(fact);
        return fact;
    }


    /**
     * 获取所有子节点
     *
     * @param fact
     */
    public void getChildFactList(Fact fact) {
        if (fact.getAtomicId() == 0) {
            List<Fact> childFacts = new ArrayList<>();
            List<FactConnective> factConnectiveList = FactConnectiveDao.getListByParentFact(fact.getId());
            if (factConnectiveList != null && factConnectiveList.size() > 0) {
                fact.setConnective(factConnectiveList.get(0).getConnective());
                for (int i = 0; i < factConnectiveList.size(); i++) {
                    FactConnective factConnective = factConnectiveList.get(i);
                    Fact childFact = FactDao.getById(factConnective.getChildFact());
                    getChildFactList(childFact);
                    childFacts.add(childFact);
                }
            }
            fact.setChildren(childFacts);
        }
    }


}
