package org.example.validator;

import org.example.validator.property.IntegerPropertyGetter;
import org.example.validator.validator.FieldNullValidation;
import org.example.validator.validator.GreaterThanValidation;
import org.example.validator.validator.LessThanValidation;


public class IntegerFluentValidator<T> extends FluentValidator<T> {
    private final IntegerPropertyGetter<T> getField;
    private final String fieldName;

    public IntegerFluentValidator(IntegerPropertyGetter<T> getField, String fieldName, Validator<T> validator) {
        this.getField = getField;
        this.fieldName = fieldName;
        validators.add(validator);
    }

    public IntegerFluentValidator<T> notNull() {
        validators.add(FieldNullValidation.notNull(getField, fieldName));
        return this;
    }

    public IntegerFluentValidator<T> greaterThan(Integer num) {
        validators.add(GreaterThanValidation.greaterThan(getField, fieldName, num));
        return this;
    }

    public IntegerFluentValidator<T> lessThan(Integer num) {
        validators.add(LessThanValidation.lessThan(getField, fieldName, num));
        return this;
    }
}
