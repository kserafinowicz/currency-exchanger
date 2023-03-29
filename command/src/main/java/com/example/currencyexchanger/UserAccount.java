package com.example.currencyexchanger;

import com.example.currencyexchanger.commands.CreateUserAccount;
import com.example.currencyexchanger.commands.Exchange;
import com.example.currencyexchanger.events.AmountExchanged;
import com.example.currencyexchanger.events.UserAccountCreated;

import java.util.Set;

public record UserAccount(AccountId accountId, String name, String surname, Set<CurrencyBalance> accounts) {
    public static UserAccountCreated apply(CreateUserAccount command) {
        return new UserAccountCreated(
                new UserAccount(
                        new AccountId(),
                        command.name(),
                        command.surname(),
                        Set.of(new CurrencyBalance(command.baseCurrency(), command.initialBalance()))
                )
        );
    }

    public AmountExchanged apply(Exchange command) {
        return new AmountExchanged(
                new UserAccount(
                        this.accountId,
                        this.name,
                        this.surname,
                        accounts.
                )
        );
    }
}
