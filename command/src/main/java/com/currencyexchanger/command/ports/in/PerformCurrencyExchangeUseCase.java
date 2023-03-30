package com.currencyexchanger.command.ports.in;

import com.currencyexchanger.command.commands.Exchange;
import com.currencyexchanger.command.events.AmountExchanged;
import com.currencyexchanger.common.errors.Error;
import io.vavr.collection.Seq;
import io.vavr.control.Either;

public interface PerformCurrencyExchangeUseCase {

  Either<Seq<Error>, AmountExchanged> performExchange(Exchange command);
}
