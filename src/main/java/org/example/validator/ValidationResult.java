package org.example.validator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ValidationResult {
    private boolean success;
    private String description;

    public static ValidationResult error(String errorText) {
        return new ValidationResult(false, errorText);
    }
    public static final ValidationResult VALID_RESULT = new ValidationResult(true, null);
}
