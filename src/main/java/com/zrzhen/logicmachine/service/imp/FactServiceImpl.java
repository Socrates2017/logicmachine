package com.zrzhen.logicmachine.service.imp;


import com.zrzhen.logicmachine.dao.AtomicFactMapper;
import com.zrzhen.logicmachine.dao.FactConnectiveMapper;
import com.zrzhen.logicmachine.dao.FactMapper;
import com.zrzhen.logicmachine.dao.GodSqlMapper;
import com.zrzhen.logicmachine.domain.AtomicFact;
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
import java.util.Map;

/**
 * @author chenanlian
 */
@Service
public class FactServiceImpl implements FactService {

    private static final Logger log = LoggerFactory.getLogger(FactServiceImpl.class);

    @Autowired
    FactConnectiveMapper factConnectiveMapper;

    @Autowired
    FactMapper factMapper;

    @Autowired
    AtomicFactMapper atomicFactMapper;

    @Autowired
    GodSqlMapper godSqlMapper;

    @Override
    public Fact getFactTree(Integer factId) {
        Fact fact = factMapper.selectByPrimaryKey(factId);
        getChildFactList(fact);
        return fact;
    }

    @Override
    public Fact getFactTreeAndSetAtomicValue(Integer factId, Integer customerId) {
        Fact fact = factMapper.selectByPrimaryKey(factId);
        getChildFactListAndSetAtomicValue(fact, customerId);
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
            List<FactConnective> factConnectiveList = factConnectiveMapper.selectByParentFact(fact.getFactId());
            if (factConnectiveList != null && factConnectiveList.size() > 0) {
                fact.setConnective(factConnectiveList.get(0).getConnective());
                for (int i = 0; i < factConnectiveList.size(); i++) {
                    FactConnective factConnective = factConnectiveList.get(i);
                    Fact childFact = factMapper.selectByPrimaryKey(factConnective.getChildFact());
                    getChildFactList(childFact);
                    childFacts.add(childFact);
                }
            }
            fact.setChildFacts(childFacts);
        }
    }

    public void getChildFactListAndSetAtomicValue(Fact fact, Integer customerId) {
        if (fact.getAtomicId() == 0) {
            List<Fact> childFacts = new ArrayList<>();
            List<FactConnective> factConnectiveList = factConnectiveMapper.selectByParentFact(fact.getFactId());
            if (factConnectiveList != null && factConnectiveList.size() > 0) {
                fact.setConnective(factConnectiveList.get(0).getConnective());
                for (int i = 0; i < factConnectiveList.size(); i++) {
                    FactConnective factConnective = factConnectiveList.get(i);
                    Fact childFact = factMapper.selectByPrimaryKey(factConnective.getChildFact());
                    getChildFactListAndSetAtomicValue(childFact, customerId);
                    childFacts.add(childFact);
                }
            }
            fact.setChildFacts(childFacts);
        } else {
            Integer atomicId = fact.getAtomicId();
            byte value = getAtomicValue(atomicId, customerId);
            fact.setValue(value);
        }
    }

    @Override
    public byte getAtomicValue(Integer atomicId, Integer customerId) {
        AtomicFact atomicFact = atomicFactMapper.selectByPrimaryKey(atomicId);
        String sql = "SELECT COUNT(1) FROM " + atomicFact.getTableName() +
                " WHERE customer_id = " + customerId + " AND "
                + atomicFact.getColumnName() + " " + atomicFact.getOperator() + " '" + atomicFact.getValue() + "'";
        List<Map<String, Object>> result = godSqlMapper.query(sql);
        Long count = (Long) result.get(0).get("COUNT(1)");
        return (byte) (count > 0 ? 1 : -1);
    }
}
