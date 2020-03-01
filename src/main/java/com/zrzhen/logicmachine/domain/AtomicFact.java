package com.zrzhen.logicmachine.domain;

import java.io.Serializable;

public class AtomicFact implements Serializable {
    private Integer atomicFactId;

    private String tableName;

    private String columnName;

    private String operator;

    private String value;

    private static final long serialVersionUID = 1L;

    public Integer getAtomicFactId() {
        return atomicFactId;
    }

    public void setAtomicFactId(Integer atomicFactId) {
        this.atomicFactId = atomicFactId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName == null ? null : columnName.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", atomicFactId=").append(atomicFactId);
        sb.append(", tableName=").append(tableName);
        sb.append(", columnName=").append(columnName);
        sb.append(", operator=").append(operator);
        sb.append(", value=").append(value);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}