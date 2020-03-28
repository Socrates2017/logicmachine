package com.zrzhen.logicmachine.drools;

public enum OperatorEnum {

    LESS( "<"),
    LESS_EQUALS("<="),
    LARGER( ">"),
    LARGER_EQUALS(">=")
    ;

    private String message;

    OperatorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
