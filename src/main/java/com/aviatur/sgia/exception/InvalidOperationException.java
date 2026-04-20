package com.aviatur.sgia.exception;

public class InvalidOperationException extends BusinessException {

    public InvalidOperationException(String message) {
        super(message, "INVALID_OPERATION");
    }
}
