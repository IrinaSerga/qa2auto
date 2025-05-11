package com.autoqa.qa2auto.exception.serviceException;

public class SubsystemServiceException extends RuntimeException {
    public SubsystemServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubsystemServiceException(String message) {
        super(message);
    }
}
