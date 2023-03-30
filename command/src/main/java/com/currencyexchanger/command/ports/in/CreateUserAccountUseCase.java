package com.currencyexchanger.command.ports.in;

import com.currencyexchanger.command.events.UserAccountCreated;
import com.currencyexchanger.command.commands.CreateUserAccount;
import com.currencyexchanger.common.errors.Error;
import io.vavr.collection.Seq;
import io.vavr.control.Either;

public interface CreateUserAccountUseCase {

    Either<Seq<Error>, UserAccountCreated> createUserAccount(
        CreateUserAccount command);
}
