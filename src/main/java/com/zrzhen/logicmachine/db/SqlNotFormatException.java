package com.zrzhen.logicmachine.db;

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
