package com.zrzhen.logicmachine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zrzhen.logicmachine.util.JsonUtil;
import com.zrzhen.logicmachine.zatis.anno.Column;

import java.io.Serializable;
import java.util.List;

/**
 * 事实类
 */
public class Fact implements Serializable {

    /**
     * 事实类型，0为默认普通事实（或原子事实），1为中间事实，2为根事实
     */
    private Integer type;

    /**
     * 事实名称
     */
    private String name;

    /**
     * 事实id
     */
    private Long id;

    /**
     * 对应的原子事实id，如果为0则表示为非原子事实
     */
    @JsonProperty("atomic_id")
    @Column(name = "atomic_id")
    private Long atomicId;

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
    private Integer value = 0;

    /**
     * 真值计算的顺序
     */
    @JsonProperty("calculate_index")
    private Integer calculateIndex;


    public Fact() {
    }

    public Fact(Long id) {
        this.id = id;
    }

    public Fact(Long id, Long atomicId) {
        this.id = id;
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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtomicId() {
        return atomicId;
    }

    public void setAtomicId(Long atomicId) {
        this.atomicId = atomicId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return JsonUtil.entity2Json(this);
    }
}