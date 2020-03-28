package com.zrzhen.logicmachine.drools;

import com.zrzhen.logicmachine.domain.AtomicFact;
import com.zrzhen.logicmachine.domain.AtomicFactParam;

import java.util.List;

public interface AtomicFactFunction<T> {


    public T execute(AtomicFact atomicFact, List<AtomicFactParam> atomicFactParamList);
}
