package org.example.validator;

import org.example.validator.property.IntegerPropertyGetter;
import org.example.validator.validator.FieldNullValidation;
import org.example.validator.validator.MoreThanValidation;


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

    public IntegerFluentValidator<T> moreThan(int num) {
        validators.add(MoreThanValidation.moreThan(getField, fieldName, num));
        return this;
    }
}
