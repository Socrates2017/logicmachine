package com.zrzhen.logicmachine.domain;

import java.io.Serializable;
import java.util.Date;

public class FactConnective implements Serializable {
    private Long parentFact;

    private Long childFact;

    private String connective;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public FactConnective() {
    }

    public Long getParentFact() {
        return parentFact;
    }

    public void setParentFact(Long parentFact) {
        this.parentFact = parentFact;
    }

    public Long getChildFact() {
        return childFact;
    }

    public void setChildFact(Long childFact) {
        this.childFact = childFact;
    }

    public String getConnective() {
        return connective;
    }

    public void setConnective(String connective) {
        this.connective = connective;
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