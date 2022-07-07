package com.ms.dao;

public class ItemEmptyException extends Exception {
    public ItemEmptyException(String message) {
        super(message);
    }

    public ItemEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
