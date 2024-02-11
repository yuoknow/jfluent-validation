package org.example.validator;

import org.example.validator.result.ValidationResult;

public interface Validator<T> {

    ValidationResult validate(T element);

}
