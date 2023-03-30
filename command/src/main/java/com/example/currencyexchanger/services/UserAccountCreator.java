package com.example.currencyexchanger.services;

import com.example.currencyexchanger.UserAccount;
import com.example.currencyexchanger.commands.CreateUserAccount;
import com.example.currencyexchanger.errors.DomainError;
import com.example.currencyexchanger.events.UserAccountCreated;
import com.example.currencyexchanger.ports.in.CreateUserAccountUseCase;
import com.example.currencyexchanger.ports.out.SaveUserAccountPort;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAccountCreator implements CreateUserAccountUseCase {

    private final SaveUserAccountPort saveUserAccountPort;

    @Override
    public Either<? extends Seq<DomainError>, UserAccountCreated> createUserAccount(CreateUserAccount command) {
        return UserAccount.apply(command).peek(saveUserAccountPort::save);
    }
}
