package com.currencyexchanger.command.validators;

import com.currencyexchanger.command.commands.CreateUserAccount;
import com.currencyexchanger.common.errors.DomainError;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

public class CreateUserValidator {

  Predicate<String> NOT_BLANK = StringUtils::isNotBlank;
  Predicate<BigDecimal> NOT_NEGATIVE = balance -> Objects.nonNull(balance) &&
      (balance.setScale(2, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO) >= 0);

  public Validation<Seq<DomainError>, CreateUserAccount> validate(
      CreateUserAccount command) {
    return Validation.combine(
        validateName(command.name()),
        validateSurname(command.surname()),
        validateBalance(command.initialBalance())
    ).ap((name, surname, balance) -> command);
  }

  private Validation<DomainError, String> validateName(String name) {
    return NOT_BLANK.test(name)
        ? Validation.valid(name)
        : Validation.invalid(new DomainError("Name is blank"));
  }

  private Validation<DomainError, String> validateSurname(String surname) {
    return NOT_BLANK.test(surname)
        ? Validation.valid(surname)
        : Validation.invalid(new DomainError("Surname is blank"));
  }

  private Validation<DomainError, BigDecimal> validateBalance(BigDecimal balance) {
    return NOT_NEGATIVE.test(balance)
        ? Validation.valid(balance)
        : Validation.invalid(new DomainError("Balance is null or negative"));
  }
}
