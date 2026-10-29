package com.innowise.array.exception;

public class InvalidArrayException extends RuntimeException {
    public InvalidArrayException() {
        super();
    }

    public InvalidArrayException(String message) {
        super(message);
    }

    public InvalidArrayException(Throwable cause) {
        super(cause);
    }

    public InvalidArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
