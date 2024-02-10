package org.example;

import lombok.Getter;
import org.example.validator.FluentValidator;
import org.example.validator.result.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Example {


    @Test
    void validation_example() {
        var validator = new FluentValidator<Customer>()
                .property(Customer::getName).notNull().notEmpty()
                .property(Customer::getAge).moreThan(10);

        var validationResult = validator.validate(new Customer());

        assertThat(validationResult.getErrorText())
                .isEqualTo("getName is null; getName is empty; getAge is lesser than 10");
    }


    @Getter
    public static class Customer {
        private String name;
        private int age;
    }
}
