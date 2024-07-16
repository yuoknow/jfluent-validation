package org.example.validator;

import org.example.validator.property.ObjectPropertyGetter;
import org.example.validator.validator.FieldNullValidation;

public class ObjectFluentValidator<T, E> extends FluentValidator<T> {
    private final ObjectPropertyGetter<T, E> getField;
    private final String fieldName;

    public ObjectFluentValidator(ObjectPropertyGetter<T, E> getField, String fieldName,
                                 Validator<T> validator,
                                 Validator<E> objValidator) {
        this.getField = getField;
        this.fieldName = fieldName;
        validators.add(validator);
        validators.add(obj -> objValidator.validate(getField.get(obj)));
    }

    public ObjectFluentValidator<T, E> notNull() {
        validators.add(FieldNullValidation.notNull(getField, fieldName));
        return this;
    }
}
