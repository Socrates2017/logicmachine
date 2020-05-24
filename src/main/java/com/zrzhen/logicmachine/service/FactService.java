package com.zrzhen.logicmachine.service;


import com.zrzhen.logicmachine.domain.Fact;

/**
 * @author chenanlian
 */
public interface FactService {

    Fact getFactTree(Long factId);
}
