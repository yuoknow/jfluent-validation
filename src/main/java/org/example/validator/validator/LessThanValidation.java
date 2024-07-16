package org.example.validator.validator;

import de.cronn.reflection.util.TypedPropertyGetter;
import org.example.validator.Validator;
import org.example.validator.result.ValidationResult;


public class LessThanValidation<T, E> implements Validator<T> {
    private final TypedPropertyGetter<T, ? extends Comparable<E>> getField;
    private final String fieldName;
    private final E value;

    public LessThanValidation(TypedPropertyGetter<T, ? extends Comparable<E>> getField, String fieldName, E value) {
        this.getField = getField;
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public ValidationResult validate(T element) {
        Comparable<E> result = getField.get(element);
        if (result == null || result.compareTo(value) > 0) {
            return ValidationResult.error(fieldName + " is more than " + value);
        } else {
            return ValidationResult.VALID_RESULT;
        }
    }

    public static <T, E> LessThanValidation<T, E> lessThan(TypedPropertyGetter<T, ? extends Comparable<E>> getField, String fieldName, E value) {
        return new LessThanValidation<>(getField, fieldName, value);
    }
}
