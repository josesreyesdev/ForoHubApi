package com.jsrdev.ForoHub.infrastructure.exceptions;

public class ValidationIntegrity extends RuntimeException {
    public ValidationIntegrity(String message) {
        super(message);
    }
}
