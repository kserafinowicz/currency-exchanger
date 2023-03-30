package com.currencyexchanger.command.validators;

import com.currencyexchanger.command.UserAccount;
import com.currencyexchanger.command.commands.Exchange;
import com.currencyexchanger.common.errors.Error;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ExchangeValidator {

  private static final Predicate<BigDecimal> AMOUNT_NOT_NEGATIVE = amount -> Objects.nonNull(amount)
      &&
      (amount.setScale(4, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO) >= 0);

  private static final BiPredicate<BigDecimal, BigDecimal> HAS_SUFFICIENT_FUNDS = (amount, balance) ->
      Objects.nonNull(amount) && Objects.nonNull(balance) &&
          balance.setScale(4, RoundingMode.HALF_UP)
              .compareTo(amount.setScale(4, RoundingMode.HALF_UP)) >= 0;

  private static final BiPredicate<Map<String, BigDecimal>, String> HAS_CURRENCY_ACCOUNT = Map::containsKey;

  public Validation<Seq<Error>, Exchange> validate(UserAccount userAccount, Exchange command) {
    return Validation.combine(
        validateHasCurrencyAccount(userAccount.accounts(), command.fromCurrencyCode()),
        validateAmount(command.amount()),
        validateEnoughFunds(command.amount(),
            userAccount.accounts().get(command.fromCurrencyCode()))
    ).ap((currencyCode, amount, amount2) -> command);
  }

  private Validation<Error, String> validateHasCurrencyAccount(
      Map<String, BigDecimal> userAccounts, String currencyCode) {
    return HAS_CURRENCY_ACCOUNT.test(userAccounts, currencyCode)
        ? Validation.valid(currencyCode)
        : Validation.invalid(new Error("Amount is null or negative"));
  }

  private Validation<Error, BigDecimal> validateAmount(BigDecimal amount) {
    return AMOUNT_NOT_NEGATIVE.test(amount)
        ? Validation.valid(amount)
        : Validation.invalid(new Error("Amount is null or negative"));
  }

  private Validation<Error, BigDecimal> validateEnoughFunds(BigDecimal amount, BigDecimal balance) {
    return HAS_SUFFICIENT_FUNDS.test(amount, balance)
        ? Validation.valid(amount)
        : Validation.invalid(new Error("Account has insufficient funds"));
  }
}
