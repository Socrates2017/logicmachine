package com.zrzhen.logicmachine.service;


import com.zrzhen.logicmachine.domain.Fact;

/**
 * @author chenanlian
 */
public interface FactService {

    public Fact getFactTree(Integer factId);

}
