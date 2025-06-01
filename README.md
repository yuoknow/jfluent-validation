# jfluent-validation
Prototype of validation library with fluent interface

[Examples](./src/test/java/org/example/ExampleTest.java)

Simple property validator:

```java
var validator = FluentValidator.<Customer>validator()
        .property(Customer::name).notNull().notEmpty()
        .property(Customer::age).greaterThan(18);
```

Inner objects property validator:

```java
var validator = FluentValidator.<Account>validator()
        .property(Account::amount).greaterThan(200)
        .property(Account::customer,
                customerValidator -> customerValidator.property(Customer::name).notEmpty());
```

Creating custom validator with context in spring

```java

@Component
@RequiredArgsConstructor
public class AccountAlreadyExistsValidator {
    private final AccountRepository repository;

    public ValidationResult validate(CreateAccountCommand command) {
        boolean isExists = repository.existsById(command.getAccountId());
        if (isExists) {
            return ValidationResult.error("Account already exists");
        } else {
            return ValidationResult.VALID_RESULT;
        }
    }
}
```

```java

@Component
public class AccountValidator {
    private final FluentValidator<CreateAccountCommand> validator;

    public AccountValidator(AccountAlreadyExistsValidator accountAlreadyExistsValidator) {
        this.validator = FluentValidator.<CreateAccountCommand>validator()
                .property(CreateAccountCommand::getAccountId).notNull()
                .addValidator(command -> accountAlreadyExistsValidator.validate(command));
    }

    public ValidationResult validate(CreateAccountCommand command) {
        return validator.validate(command);
    }
}
```
