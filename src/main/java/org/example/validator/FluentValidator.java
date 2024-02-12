package org.example.validator;

import org.example.validator.property.IntegerPropertyGetter;
import org.example.validator.property.ObjectPropertyGetter;
import org.example.validator.property.StringPropertyGetter;
import org.example.validator.result.ValidationResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class FluentValidator<T> implements Validator<T> {
    private final String prefix;
    protected final List<Validator<T>> validators = new ArrayList<>();

    public FluentValidator() {
        this.prefix = "";
    }

    private FluentValidator(String prefix) {
        Objects.requireNonNull(prefix, "Prefix cannot be null");
        this.prefix = prefix;
    }

    public ValidationResult validate(T element) {
        var errors = validators.stream()
                .map(validator -> validator.validate(element))
                .filter(e -> e instanceof ValidationResult.Error)
                .map(ValidationResult.Error.class::cast)
                .toList();
        if (!errors.isEmpty()) {
            return ValidationResult.error(errors);
        } else {
            return ValidationResult.VALID_RESULT;
        }
    }

    public StringFluentValidator<T> property(StringPropertyGetter<T> getter) {
        return new StringFluentValidator<>(getter, getFieldName(getter.getMethodName()), this);
    }

    public IntegerFluentValidator<T> property(IntegerPropertyGetter<T> getter) {
        return new IntegerFluentValidator<>(getter, getFieldName(getter.getMethodName()), this);
    }

    public <E> FluentValidator<T> property(ObjectPropertyGetter<T, E> getter,
                                           UnaryOperator<FluentValidator<E>> validator) {
        return new ObjectFluentValidator<>(getter, getter.getMethodName(),
                this,
                validator.apply(new FluentValidator<>(getter.getMethodName())));
    }

    private String getFieldName(String name) {
        return prefix.isEmpty() ? name : prefix + "." + name;
    }

    public static <T> FluentValidator<T> validator() {
        return new FluentValidator<>();
    }

    public FluentValidator<T> addValidator(Validator<T> validator) {
        validators.add(validator);
        return this;
    }

    public FluentValidator<T> addValidators(Collection<Validator<T>> validators) {
        validators.addAll(validators);
        return this;
    }
}
