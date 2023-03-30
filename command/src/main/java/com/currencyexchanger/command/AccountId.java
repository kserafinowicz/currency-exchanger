package com.currencyexchanger.command;


import com.currencyexchanger.common.errors.Error;
import io.vavr.control.Either;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;


@Getter
public class AccountId {

    private static final Predicate<String> CONSTRAINT = Objects::nonNull;
    String uuid;

    public AccountId() {
        this.uuid = UUID.randomUUID().toString();
    }

    private AccountId(UUID uuid) {
        this.uuid = uuid.toString();
    }

    public static Either<Error, AccountId> fromString(String id) {
        return CONSTRAINT.test(id)
                ? Either.right(new AccountId(UUID.fromString(id)))
                : Either.left(new Error("Identifier is null"));
    }
}
