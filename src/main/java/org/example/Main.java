package org.example;

import lombok.Getter;
import org.example.validator.FluentValidator;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        var validator = new FluentValidator<Customer>()
                .property(Customer::getName).notNull().notEmpty()
                .property(Customer::getAge).moreThan(10);

        out.println(validator.validate(new Customer()));
    }

    public static class Customer {
        private String name;
        private int age;

        @Getter
        private Bank bank;

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

    }

    public static class Bank {

    }
}