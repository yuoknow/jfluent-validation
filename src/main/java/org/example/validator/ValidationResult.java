package org.example.validator;


import java.util.List;
import java.util.stream.Collectors;

public sealed interface ValidationResult permits ValidationResult.Success, ValidationResult.Error {

    record Success() implements ValidationResult {
    }

    static Error error(String errorText) {
        return new Error(errorText);
    }

    static Error error(List<Error> errors) {
        return new Error(errors.stream().map(Error::errorText).collect(Collectors.joining(";")));
    }

    record Error(String errorText) implements ValidationResult {}

    ValidationResult VALID_RESULT = new Success();
}
