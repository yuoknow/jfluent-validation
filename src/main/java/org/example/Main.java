package org.example;

import lombok.Getter;
import org.example.validator.FluentValidator;


public class Main {

    public static void main(String[] args) {
        var validator = new FluentValidator<Customer>()
                .property(Customer::getName).notNull().notEmpty()
                .property(Customer::getAge).moreThan(10);

        System.out.println(validator.validate(new Customer()));
    }

    @Getter
    public static class Customer {
        private String name;
        private int age;
        private Bank bank;
    }

    public static class Bank {

    }
}