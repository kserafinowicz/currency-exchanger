package com.currencyexchanger.app.adapters.out.jpa;

import com.currencyexchanger.app.adapters.out.jpa.mappers.CurrencyExchangeRateMapper;
import com.currencyexchanger.app.adapters.out.jpa.repository.ExchangeRateRepository;
import com.currencyexchanger.command.CurrencyExchangeRate;
import com.currencyexchanger.command.ports.out.FindExchangeRatePort;
import com.currencyexchanger.common.errors.Error;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindExchangeRateAdapter implements FindExchangeRatePort {

  private final ExchangeRateRepository exchangeRateRepository;

  @Override
  public Either<Error, CurrencyExchangeRate> findExchangeRate(String currency) {
    return exchangeRateRepository.findByCurrencyCode(currency).fold(
        () -> Either.left(new Error("Currency not found")),
        entity -> Either.right(CurrencyExchangeRateMapper.toDomain(entity))
    );
  }
}
