package org.example.validator.validator;

import de.cronn.reflection.util.TypedPropertyGetter;
import org.example.validator.Validator;
import org.example.validator.result.ValidationResult;


public class FieldEmptyValidation<T> implements Validator<T> {
    private final TypedPropertyGetter<T, String> getField;
    private final String fieldName;


    public FieldEmptyValidation(TypedPropertyGetter<T, String> getField, String fieldName) {
        this.getField = getField;
        this.fieldName = fieldName;
    }

    @Override
    public ValidationResult validate(T element) {
        String result = getField.get(element);
        if (result == null || result.isEmpty()) {
            return ValidationResult.error(fieldName + " is empty");
        } else {
            return ValidationResult.VALID_RESULT;
        }
    }

    public static <T> FieldEmptyValidation<T> isEmpty(TypedPropertyGetter<T, String> getField, String fieldName) {
        return new FieldEmptyValidation<>(getField, fieldName);
    }
}
