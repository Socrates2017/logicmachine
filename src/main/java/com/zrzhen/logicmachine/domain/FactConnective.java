package com.zrzhen.logicmachine.domain;

import java.io.Serializable;

public class FactConnective extends FactConnectiveKey implements Serializable {
    private String connective;

    private static final long serialVersionUID = 1L;

    public String getConnective() {
        return connective;
    }

    public void setConnective(String connective) {
        this.connective = connective == null ? null : connective.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", connective=").append(connective);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}