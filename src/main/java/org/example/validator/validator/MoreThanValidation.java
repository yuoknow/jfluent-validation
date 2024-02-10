package org.example.validator.validator;

import de.cronn.reflection.util.TypedPropertyGetter;
import lombok.RequiredArgsConstructor;
import org.example.validator.Validator;
import org.example.validator.result.ValidationResult;


@RequiredArgsConstructor
public class MoreThanValidation<T> implements Validator<T> {
    private final TypedPropertyGetter<T, Integer> getField;
    private final String fieldName;
    private final int value;


    @Override
    public ValidationResult validate(T element) {
        Integer result = getField.get(element);
        if (result == null || result < value) {
            return ValidationResult.error(fieldName + " is less than " + value);
        } else {
            return ValidationResult.VALID_RESULT;
        }
    }

    public static <T> MoreThanValidation<T> moreThan(TypedPropertyGetter<T, Integer> getField, String fieldName, int value) {
        return new MoreThanValidation<>(getField, fieldName, value);
    }
}
