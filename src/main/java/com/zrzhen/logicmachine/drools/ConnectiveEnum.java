package com.zrzhen.logicmachine.drools;

public enum ConnectiveEnum {

    AND( "AND"),
    OR("OR"),
    NOT("NOT")
    ;

    private String message;

    ConnectiveEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
