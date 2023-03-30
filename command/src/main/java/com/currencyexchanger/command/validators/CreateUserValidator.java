package com.currencyexchanger.command.validators;

import com.currencyexchanger.command.commands.CreateUserAccount;
import com.currencyexchanger.common.errors.Error;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

public class CreateUserValidator {

  private static final Predicate<String> NOT_BLANK = StringUtils::isNotBlank;
  private static final Predicate<BigDecimal> NOT_NEGATIVE = balance -> Objects.nonNull(balance) &&
      (balance.setScale(2, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO) >= 0);

  public Validation<Seq<Error>, CreateUserAccount> validate(
      CreateUserAccount command) {
    return Validation.combine(
        validateName(command.name()),
        validateSurname(command.surname()),
        validateBalance(command.initialBalance())
    ).ap((name, surname, balance) -> command);
  }

  private Validation<Error, String> validateName(String name) {
    return NOT_BLANK.test(name)
        ? Validation.valid(name)
        : Validation.invalid(new Error("Name is blank"));
  }

  private Validation<Error, String> validateSurname(String surname) {
    return NOT_BLANK.test(surname)
        ? Validation.valid(surname)
        : Validation.invalid(new Error("Surname is blank"));
  }

  private Validation<Error, BigDecimal> validateBalance(BigDecimal balance) {
    return NOT_NEGATIVE.test(balance)
        ? Validation.valid(balance)
        : Validation.invalid(new Error("Balance is null or negative"));
  }
}
