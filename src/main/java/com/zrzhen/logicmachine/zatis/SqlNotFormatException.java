package com.zrzhen.logicmachine.zatis;

/**
 * @author chenanlian
 */
public class SqlNotFormatException extends Exception {
    private final String message;

    public SqlNotFormatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
