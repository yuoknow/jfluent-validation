package org.example.validator;

import lombok.RequiredArgsConstructor;
import org.example.validator.property.IntegerPropertyGetter;
import org.example.validator.property.ObjectPropertyGetter;
import org.example.validator.property.StringPropertyGetter;
import org.example.validator.result.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class FluentValidator<T> implements Validator<T> {
    protected final List<Validator<T>> validators = new ArrayList<>();

    public ValidationResult validate(T element) {
        var errors = validators.stream()
                .map(validator -> validator.validate(element))
                .map(e -> {
                    if (e instanceof ValidationResult.Error error){
                        return error;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
        if (!errors.isEmpty()) {
            return ValidationResult.error(errors);
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
