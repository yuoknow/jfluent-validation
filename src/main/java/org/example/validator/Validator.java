package org.example.validator;

public interface Validator<T> {

    ValidationResult validate(T element);

}
