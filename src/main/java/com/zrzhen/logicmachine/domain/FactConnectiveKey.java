package com.zrzhen.logicmachine.domain;

import java.io.Serializable;

public class FactConnectiveKey implements Serializable {
    private Integer parentFact;

    private Integer childFact;

    private static final long serialVersionUID = 1L;

    public Integer getParentFact() {
        return parentFact;
    }

    public void setParentFact(Integer parentFact) {
        this.parentFact = parentFact;
    }

    public Integer getChildFact() {
        return childFact;
    }

    public void setChildFact(Integer childFact) {
        this.childFact = childFact;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", parentFact=").append(parentFact);
        sb.append(", childFact=").append(childFact);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}