package com.zrzhen.logicmachine.domain;

import com.zrzhen.logicmachine.util.JsonUtil;

import java.io.Serializable;
import java.util.List;

public class Fact implements Serializable {
    private Integer factId;

    private Integer atomicId;

    private List<Fact> childFacts;

    private String childFactsConnective;

    public Fact(Integer factId) {
        this.factId = factId;
    }

    public Fact(Integer factId, Integer atomicId) {
        this.factId = factId;
        this.atomicId = atomicId;
    }


    private static final long serialVersionUID = 1L;

    public Integer getFactId() {
        return factId;
    }

    public void setFactId(Integer factId) {
        this.factId = factId;
    }

    public Integer getAtomicId() {
        return atomicId;
    }

    public void setAtomicId(Integer atomicId) {
        this.atomicId = atomicId;
    }

    public List<Fact> getChildFacts() {
        return childFacts;
    }

    public void setChildFacts(List<Fact> childFacts) {
        this.childFacts = childFacts;
    }

    public String getChildFactsConnective() {
        return childFactsConnective;
    }

    public void setChildFactsConnective(String childFactsConnective) {
        this.childFactsConnective = childFactsConnective;
    }

    @Override
    public String toString() {
        return JsonUtil.obj2Json(this);
    }
}