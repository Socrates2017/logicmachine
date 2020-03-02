package com.zrzhen.logicmachine.service;


import com.zrzhen.logicmachine.domain.Fact;

/**
 * @author chenanlian
 */
public interface FactService {

    Fact getFactTree(Integer factId);

    Fact getFactTreeAndSetAtomicValue(Integer factId,Integer customerId);

    /**
     * 根据用户id和原子实时id查询真值
     * @param atomicId
     * @param customerId
     * @return
     */
    byte getAtomicValue(Integer atomicId, Integer customerId);
}
