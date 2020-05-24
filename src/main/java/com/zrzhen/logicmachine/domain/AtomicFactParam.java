package com.zrzhen.logicmachine.domain;

import java.io.Serializable;

public class AtomicFactParam implements Serializable {

    private Integer atomicFactId;

    private Short index;

    private String name;

    private String value;

    private String type;

    private static final long serialVersionUID = 1L;


    public AtomicFactParam() {
    }

    public Integer getAtomicFactId() {
        return atomicFactId;
    }

    public void setAtomicFactId(Integer atomicFactId) {
        this.atomicFactId = atomicFactId;
    }

    public Short getIndex() {
        return index;
    }

    public void setIndex(Short index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}