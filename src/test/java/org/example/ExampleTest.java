package org.example;

import org.example.validator.FluentValidator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExampleTest {


    @Test
    void validation_example() {
        var validator = FluentValidator.<Customer>validator()
                .property(Customer::name).notNull().notEmpty()
                .property(Customer::age).notNull().greaterThan(10);

        var validationResult = validator.validate(new Customer("", 1));

        assertThat(validationResult.getErrorText())
                .isEqualTo("name is empty; age is less than 10");
    }

    @Test
    void inner_object_validation_example() {
        var validator = FluentValidator.<Account>validator()
                .property(Account::amount).greaterThan(200)
                .property(Account::customer,
                        customerValidator -> customerValidator.property(Customer::name).notEmpty());

        var validationResult = validator
                .validate(new Account(new Customer("", 1), 100));

        assertThat(validationResult.getErrorText())
                .isEqualTo("amount is less than 200; customer.name is empty");
    }

    public record Account(Customer customer, int amount) {
    }

    public record Customer(String name, int age) {
    }
}
