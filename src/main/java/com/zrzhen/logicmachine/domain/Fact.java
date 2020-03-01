package com.zrzhen.logicmachine.domain;

import com.zrzhen.logicmachine.util.JsonUtil;

import java.io.Serializable;
import java.util.List;

public class Fact implements Serializable {

    /**
     * 事实id
     */
    private Integer factId;

    /**
     * 对应的原子事实id，如果为0则表示为非原子事实
     */
    private Integer atomicId;

    /**
     * 子节点
     */
    private List<Fact> childFacts;

    /**
     * 子节点的联结符
     */
    private String childFactsConnective;

    /**
     * 真值，0为为判定，-1为假，1为真
     */
    private byte value;

    public Fact(Integer factId) {
        this.factId = factId;
    }

    public Fact(Integer factId, Integer atomicId) {
        this.factId = factId;
        this.atomicId = atomicId;
    }


    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

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