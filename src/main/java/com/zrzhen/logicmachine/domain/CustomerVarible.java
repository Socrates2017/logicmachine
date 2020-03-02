package com.zrzhen.logicmachine.domain;

import java.io.Serializable;
import java.util.Date;

public class CustomerVarible implements Serializable {
    private Integer customerVaribleId;

    private Integer callTimeLongSumLastWeek;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getCustomerVaribleId() {
        return customerVaribleId;
    }

    public void setCustomerVaribleId(Integer customerVaribleId) {
        this.customerVaribleId = customerVaribleId;
    }

    public Integer getCallTimeLongSumLastWeek() {
        return callTimeLongSumLastWeek;
    }

    public void setCallTimeLongSumLastWeek(Integer callTimeLongSumLastWeek) {
        this.callTimeLongSumLastWeek = callTimeLongSumLastWeek;
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
        sb.append(", customerVaribleId=").append(customerVaribleId);
        sb.append(", callTimeLongSumLastWeek=").append(callTimeLongSumLastWeek);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}