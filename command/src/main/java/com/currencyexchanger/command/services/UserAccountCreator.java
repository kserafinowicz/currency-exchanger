package com.currencyexchanger.command.services;

import com.currencyexchanger.command.UserAccount;
import com.currencyexchanger.command.commands.CreateUserAccount;
import com.currencyexchanger.command.events.UserAccountCreated;
import com.currencyexchanger.command.ports.in.CreateUserAccountUseCase;
import com.currencyexchanger.command.ports.out.SaveUserAccountPort;
import com.currencyexchanger.common.errors.DomainError;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAccountCreator implements CreateUserAccountUseCase {

    private final SaveUserAccountPort saveUserAccountPort;

    @Override
    public Either<Seq<DomainError>, UserAccountCreated> createUserAccount(
        CreateUserAccount command) {
        return UserAccount.apply(command).peek(saveUserAccountPort::save);
    }
}
