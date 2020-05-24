package com.zrzhen.logicmachine.constant;

public enum FactValueEnum {

    /**
     * 未知的，计算之前的
     */
    UNKNOWN(0),

    /**
     * 真
     */
    TRUE(1),

    /**
     * 假
     */
    FALSE(-1),

    /**
     * 异常
     */
    EXCEPTION(2),

    ;

    private int code;

    FactValueEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
