package com.zrzhen.logicmachine.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zrzhen.logicmachine.zatis.anno.Column;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


public class AtomicFact implements Serializable {


    private Long id;

    @NotNull
    @Column(name = "atomic_fact_function")
    @JsonProperty("atomic_fact_function")
    private String atomicFactFunction;

    @NotNull
    private String operator;

    @NotNull
    private String value;

    @Column(name = "create_time")
    @JsonProperty("create_time")
    private Date createTime;

    @Column(name = "update_time")
    @JsonProperty("update_time")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public AtomicFact() {
    }

    public AtomicFact(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtomicFactFunction() {
        return atomicFactFunction;
    }

    public void setAtomicFactFunction(String atomicFactFunction) {
        this.atomicFactFunction = atomicFactFunction;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}