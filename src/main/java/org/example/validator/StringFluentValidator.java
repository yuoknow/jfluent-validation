package org.example.validator;

import org.example.validator.property.StringPropertyGetter;
import org.example.validator.validator.FieldEmptyValidation;
import org.example.validator.validator.FieldNullValidation;

import java.util.List;

public class StringFluentValidator<T> extends FluentValidator<T> {
    private final StringPropertyGetter<T> getField;
    private final String fieldName;

    public StringFluentValidator(StringPropertyGetter<T> getField, String fieldName, Validator<T> validator) {
        this.getField = getField;
        this.fieldName = fieldName;
        validators.add(validator);
    }

    public StringFluentValidator<T> notNull() {
        validators.add(FieldNullValidation.notNull(getField, fieldName));
        return this;
    }

    public StringFluentValidator<T> notEmpty() {
        validators.add(FieldEmptyValidation.isEmpty(getField, fieldName));
        return this;
    }
}
