package org.example;

import org.example.validator.FluentValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Example {


    @Test
    void validation_example() {
        var validator = new FluentValidator<Customer>()
                .property(Customer::name).notNull().notEmpty()
                .property(Customer::age).moreThan(10);

        var validationResult = validator.validate(new Customer("", 1));

        assertThat(validationResult.getErrorText())
                .isEqualTo("name is empty; age is less than 10");
    }

    record Customer(String name, int age) {
    }
}
