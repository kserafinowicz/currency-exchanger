package com.currencyexchanger.command.services;

import com.currencyexchanger.command.commands.Exchange;
import com.currencyexchanger.command.events.AmountExchanged;
import com.currencyexchanger.command.ports.in.PerformCurrencyExchangeUseCase;
import com.currencyexchanger.command.ports.out.FindUserAccountPort;
import com.currencyexchanger.command.ports.out.SaveUserAccountPort;
import com.currencyexchanger.common.errors.Error;
import io.vavr.collection.Array;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CurrencyExchangePerformer implements PerformCurrencyExchangeUseCase {

  private final FindUserAccountPort findUserAccountPort;
  private final SaveUserAccountPort saveUserAccountPort;


  @Override
  public Either<Seq<Error>, AmountExchanged> performExchange(Exchange command) {
    return findUserAccountPort.findUserAccount(command.account().getUuid()).fold(
        () -> Either.left(Array.of(new Error("Cannot find user account"))),
        userAccount -> userAccount.apply(command).peek(saveUserAccountPort::save)
    );
  }
}
