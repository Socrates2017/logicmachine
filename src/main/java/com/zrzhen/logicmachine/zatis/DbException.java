package com.zrzhen.logicmachine.zatis;

/**
 * @author chenanlian
 */
public class DbException extends Exception {
    private final String message;

    public DbException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
