package com.aviatur.sgia.exception;

public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException(String message) {
        super(message, "DUPLICATE_RESOURCE");
    }
}
