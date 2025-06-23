package com.autoqa.qa2auto.exception.validationException;

import com.autoqa.qa2auto.validator.Error;
import lombok.Getter;

import java.util.List;

public class ValidationException extends RuntimeException{

    @Getter
    private final List<com.autoqa.qa2auto.validator.Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
