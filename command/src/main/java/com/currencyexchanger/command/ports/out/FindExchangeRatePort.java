package com.currencyexchanger.command.ports.out;

import com.currencyexchanger.command.CurrencyExchangeRate;
import com.currencyexchanger.common.errors.Error;
import io.vavr.control.Either;

public interface FindExchangeRatePort {

  Either<Error, CurrencyExchangeRate> findExchangeRate(String currency);
}
