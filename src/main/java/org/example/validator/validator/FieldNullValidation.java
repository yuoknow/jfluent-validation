package org.example.validator.validator;

import org.example.validator.Validator;
import org.example.validator.property.RefinedPropertyGetter;
import org.example.validator.result.ValidationResult;


public class FieldNullValidation<T> implements Validator<T> {
    private final RefinedPropertyGetter<T, ? extends Object> getField;
    private final String fieldName;


    public FieldNullValidation(RefinedPropertyGetter<T, ? extends Object> getField, String fieldName) {
        this.getField = getField;
        this.fieldName = fieldName;
    }

    @Override
    public ValidationResult validate(T element) {
        var result = getField.get(element);
        if (result == null) {
            return ValidationResult.error(fieldName + " is null");
        } else {
            return ValidationResult.VALID_RESULT;
        }
    }

    public static <T> FieldNullValidation<T> notNull(RefinedPropertyGetter<T, ? extends Object> getField, String fieldName) {
        return new FieldNullValidation<>(getField, fieldName);
    }
}
