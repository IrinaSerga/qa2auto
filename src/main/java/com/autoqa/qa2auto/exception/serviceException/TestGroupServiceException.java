package com.autoqa.qa2auto.exception.serviceException;

public class TestGroupServiceException extends RuntimeException {
    public TestGroupServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public TestGroupServiceException(String message) {
        super(message);
    }
}
