package com.zrzhen.logicmachine.domain;

import java.io.Serializable;

public class AtomicFactParamKey implements Serializable {
    private Integer atomicFactId;

    private Short index;

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", atomicFactId=").append(atomicFactId);
        sb.append(", index=").append(index);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}