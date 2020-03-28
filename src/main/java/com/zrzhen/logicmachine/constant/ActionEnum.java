package com.zrzhen.logicmachine.constant;

import com.zrzhen.logicmachine.drools.Action;

import java.lang.reflect.Method;

public enum ActionEnum {

    SET_RESULT("test", Action.setResult);


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
