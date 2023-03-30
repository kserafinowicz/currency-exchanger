package com.currencyexchanger.command;

import com.currencyexchanger.command.commands.Exchange;
import com.currencyexchanger.command.events.AmountExchanged;
import com.currencyexchanger.command.events.UserAccountCreated;
import com.currencyexchanger.command.commands.CreateUserAccount;
import com.currencyexchanger.command.validators.CreateUserValidator;
import com.currencyexchanger.common.errors.DomainError;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public record UserAccount(AccountId accountId, String name, String surname, Map<String, BigDecimal> accounts) {
    public static Either<Seq<DomainError>, UserAccountCreated> apply(CreateUserAccount command) {
        return new CreateUserValidator().validate(command).fold(
            Either::left,
            result -> Either.right(new UserAccountCreated(
                new UserAccount(
                    new AccountId(),
                    command.name(),
                    command.surname(),
                    Map.of(command.baseCurrency().getCurrencyCode(), command.initialBalance())
                )
            ))
        );
    }

    private AmountExchanged apply(Exchange command) {
        return new AmountExchanged(
                new UserAccount(
                        this.accountId,
                        this.name,
                        this.surname,
                        command.fromCurrencyCode().equals(command.baseCurrency().getCurrencyCode())
                                ? buyCurrency(this.accounts, command)
                                : sellCurrency(this.accounts, command)
                )
        );
    }

    private Map<String, BigDecimal> buyCurrency(Map<String, BigDecimal> accounts, Exchange command) {
        Map<String, BigDecimal> accountsCopy = new HashMap<>(accounts);

        accountsCopy.replace(command.fromCurrencyCode(), accounts.get(command.toCurrencyCode()).subtract(command.amount()));

        BigDecimal exchanged = command.exchangeRate().bid().multiply(command.amount());
        if (!accountsCopy.containsKey(command.toCurrencyCode())) {
            accountsCopy.put(command.toCurrencyCode(), exchanged);
        } else {
            accountsCopy.put(command.toCurrencyCode(), accountsCopy.get(command.toCurrencyCode()).add(exchanged));
        }
        return accountsCopy;
    }

    private Map<String, BigDecimal> sellCurrency(Map<String, BigDecimal> accounts, Exchange command) {
        Map<String, BigDecimal> accountsCopy = new HashMap<>(accounts);

        accountsCopy.replace(command.fromCurrencyCode(), accounts.get(command.toCurrencyCode()).subtract(command.amount()));

        BigDecimal exchanged = command.amount().divide(command.exchangeRate().ask(), RoundingMode.HALF_UP);
        accountsCopy.put(command.toCurrencyCode(), accountsCopy.get(command.toCurrencyCode()).add(exchanged));
        return accountsCopy;
    }
}
