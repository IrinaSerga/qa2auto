package com.autoqa.qa2auto.validator;

public interface Validator <T> {

    ValidationResult isValid(T object);

}
