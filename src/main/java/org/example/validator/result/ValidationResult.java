package org.example.validator.result;


import java.util.List;
import java.util.stream.Collectors;

public sealed interface ValidationResult permits ValidationResult.Error, ValidationResult.Success {

    default String getErrorText() {
        return switch (this){
            case Error error -> error.errorText();
            case Success success -> "";
        };
    }

    record Success() implements ValidationResult {}

    static Error error(String errorText) {
        return new Error(errorText);
    }

    static Error error(List<Error> errors) {
        return new Error(errors.stream().map(Error::errorText).collect(Collectors.joining("; ")));
    }

    record Error(String errorText) implements ValidationResult {}

    ValidationResult VALID_RESULT = new Success();
}
