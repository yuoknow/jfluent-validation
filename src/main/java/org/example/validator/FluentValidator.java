package org.example.validator;

import lombok.RequiredArgsConstructor;
import org.example.validator.property.IntegerPropertyGetter;
import org.example.validator.property.ObjectPropertyGetter;
import org.example.validator.property.StringPropertyGetter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FluentValidator<T> implements Validator<T> {
    protected final List<Validator<T>> validators = new ArrayList<>();

    public ValidationResult validate(T element) {
        var errors = validators.stream()
                .map(validator -> validator.validate(element))
                .filter(r -> !r.isSuccess())
                .map(ValidationResult::getDescription)
                .toList();
        if (!errors.isEmpty()) {
            return ValidationResult.error(String.join("; ", errors));
        } else {
            return ValidationResult.VALID_RESULT;
        }
    }

    public StringFluentValidator<T> property(StringPropertyGetter<T> getter) {
        return new StringFluentValidator<>(getter, getter.getMethodName(), this);
    }

    public IntegerFluentValidator<T> property(IntegerPropertyGetter<T> getter) {
        return new IntegerFluentValidator<>(getter, getter.getMethodName(), this);
    }

    public FluentValidator<T> property(ObjectPropertyGetter<T> getter) {
        return null;
    }
}
