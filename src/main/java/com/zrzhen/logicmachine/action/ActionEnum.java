package com.zrzhen.logicmachine.action;

import java.lang.reflect.Method;

public enum ActionEnum {

    /**
     * 审核通过
     */
    RISK_CONFIRM("RiskConfirm", RiskConfirm.execute),

    /**
     * 审核拒绝
     */
    RISK_REFUSE("RiskRefuse", RiskConfirm.execute),

    /**
     * 人审1
     */
    RISK_AUDIT1("RiskAudit1", RiskConfirm.execute),

    /**
     * 人审2
     */
    RISK_AUDIT2("RiskAudit2", RiskConfirm.execute),
    ;


    private String name;
    private Method method;

    /**
     * 通过名称获取方法
     *
     * @param name
     * @return
     */
    public static Method getMethodByName(String name) {
        ActionEnum[] actionEnums = ActionEnum.values();
        for (int i = 0; i < actionEnums.length; i++) {
            if (actionEnums[i].getName().equals(name)) {
                return actionEnums[i].getMethod();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    ActionEnum(String name, Method method) {
        this.name = name;
        this.method = method;
    }
}
