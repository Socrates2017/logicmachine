package com.zrzhen.logicmachine.domain;

import java.io.Serializable;
import java.util.Date;

public class AtomicFact implements Serializable {

    private Integer atomicFactId;

    private String atomicFactFunction;

    private String operator;

    private String value;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;


    public AtomicFact(Integer atomicFactId) {
        this.atomicFactId = atomicFactId;
    }

    public Integer getAtomicFactId() {
        return atomicFactId;
    }

    public void setAtomicFactId(Integer atomicFactId) {
        this.atomicFactId = atomicFactId;
    }

    public String getAtomicFactFunction() {
        return atomicFactFunction;
    }

    public void setAtomicFactFunction(String atomicFactFunction) {
        this.atomicFactFunction = atomicFactFunction == null ? null : atomicFactFunction.trim();
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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