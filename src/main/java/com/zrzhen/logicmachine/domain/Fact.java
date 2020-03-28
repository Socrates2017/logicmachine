package com.zrzhen.logicmachine.domain;

import com.zrzhen.logicmachine.util.JsonUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 事实类
 */
public class Fact implements Serializable {

    /**
     * 事实类型，0为默认普通事实（或原子事实），1为中间事实，2为根事实
     */
    private Short type;

    /**
     * 事实名称
     */
    private String name;

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
    private List<Fact> children;

    /**
     * 子节点的联结符
     */
    private String connective;

    /**
     * 真值，0为未判定，-1为假，1为真
     */
    private byte value;

    /**
     * 真值计算的顺序
     */
    private Integer calculateIndex;


    public Fact() {
    }

    public Fact(Integer factId) {
        this.factId = factId;
    }

    public Fact(Integer factId, Integer atomicId) {
        this.factId = factId;
        this.atomicId = atomicId;
    }

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalculateIndex() {
        return calculateIndex;
    }

    public void setCalculateIndex(Integer calculateIndex) {
        this.calculateIndex = calculateIndex;
    }

    public List<Fact> getChildren() {
        return children;
    }

    public void setChildren(List<Fact> children) {
        this.children = children;
    }

    public String getConnective() {
        return connective;
    }

    public void setConnective(String connective) {
        this.connective = connective;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JsonUtil.entity2Json(this);
    }
}