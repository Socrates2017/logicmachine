package com.zrzhen.logicmachine.factfuntion;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 原子事实函数枚举
 */
public enum AtomicFactFunctionEnum {


    /**
     * 融360分值
     */
    R360_SORE("R360Sore", R360Sore.execute),

    /**
     * advance 分值
     */
    ADVANCE_SORE("AdvanceSore", AdvanceSore.execute),
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
        AtomicFactFunctionEnum[] all = AtomicFactFunctionEnum.values();
        for (int i = 0; i < all.length; i++) {
            if (all[i].getName().equals(name)) {
                return all[i].getMethod();
            }
        }
        return null;
    }

    public static List<String> nameList() {
        List<String> codeList = new ArrayList<>();
        AtomicFactFunctionEnum[] enums = AtomicFactFunctionEnum.values();
        for (AtomicFactFunctionEnum connectiveEnum : enums) {
            codeList.add(connectiveEnum.getName());
        }
        return codeList;
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
