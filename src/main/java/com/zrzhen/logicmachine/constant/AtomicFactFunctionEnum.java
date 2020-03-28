package com.zrzhen.logicmachine.constant;

import com.zrzhen.logicmachine.drools.InstitutionSore;

import java.lang.reflect.Method;

public enum AtomicFactFunctionEnum {


    INSTITUTION_SORE("InstitutionSore", InstitutionSore.execute);

    private String name;
    private Method method;

    /**
     * 通过名称获取方法
     *
     * @param name
     * @return
     */
    public static Method getMethodByName(String name) {
        AtomicFactFunctionEnum[] all = AtomicFactFunctionEnum.values();
        for (int i = 0; i < all.length; i++) {
            if (all[i].getName().equals(name)) {
                return all[i].getMethod();
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

    AtomicFactFunctionEnum(String name, Method method) {
        this.name = name;
        this.method = method;
    }
}
