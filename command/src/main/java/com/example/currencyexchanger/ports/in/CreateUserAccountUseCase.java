package com.example.currencyexchanger.ports.in;

import com.example.currencyexchanger.commands.CreateUserAccount;
import com.example.currencyexchanger.errors.DomainError;
import com.example.currencyexchanger.events.UserAccountCreated;
import io.vavr.collection.Seq;
import io.vavr.control.Either;

public interface CreateUserAccountUseCase {

    Either<? extends Seq<DomainError>, UserAccountCreated> createUserAccount(CreateUserAccount command);
}
