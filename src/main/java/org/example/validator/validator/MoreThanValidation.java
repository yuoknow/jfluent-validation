package org.example.validator.validator;

import de.cronn.reflection.util.TypedPropertyGetter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.validator.ValidationResult;
import org.example.validator.Validator;


@RequiredArgsConstructor
public class MoreThanValidation<T> implements Validator<T> {
    private final TypedPropertyGetter<T, Integer> getField;
    private final String fieldName;
    private final int value;


    @Override
    public ValidationResult validate(T element) {
        Integer result = getField.get(element);
        if (result == null || result < value) {
            return ValidationResult.error(fieldName + " is lesser than " + value);
        } else {
            return ValidationResult.VALID_RESULT;
        }
    }

    public static <T> MoreThanValidation<T> moreThan(TypedPropertyGetter<T, Integer> getField, String fieldName, int value) {
        return new MoreThanValidation<>(getField, fieldName, value);
    }
}
