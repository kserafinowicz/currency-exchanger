package com.currencyexchanger.command.ports.in;

import com.currencyexchanger.command.events.UserAccountCreated;
import com.currencyexchanger.command.commands.CreateUserAccount;
import com.currencyexchanger.common.errors.DomainError;
import io.vavr.collection.Seq;
import io.vavr.control.Either;

public interface CreateUserAccountUseCase {

    Either<? extends Seq<DomainError>, UserAccountCreated> createUserAccount(
        CreateUserAccount command);
}
