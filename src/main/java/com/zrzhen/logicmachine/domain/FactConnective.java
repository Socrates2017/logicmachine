package com.zrzhen.logicmachine.domain;

import java.io.Serializable;
import java.util.Date;

public class FactConnective extends FactConnectiveKey implements Serializable {
    private String connective;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public String getConnective() {
        return connective;
    }

    public void setConnective(String connective) {
        this.connective = connective == null ? null : connective.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", connective=").append(connective);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}