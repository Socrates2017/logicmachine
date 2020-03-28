package com.zrzhen.logicmachine.drools;

import java.lang.reflect.Method;

public class Action {


    private Integer id;


    public static Method setResult;

    {
        try {
            setResult = Action.class.getMethod("setResult", int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static int setResult(int result) {

        if (result != 0) {
            //保存结果到审核结果表中
        }
        return result;
    }
}
