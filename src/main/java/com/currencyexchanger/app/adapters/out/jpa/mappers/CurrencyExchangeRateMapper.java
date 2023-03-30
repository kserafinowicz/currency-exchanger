package com.currencyexchanger.app.adapters.out.jpa.mappers;

import com.currencyexchanger.app.adapters.out.jpa.entities.ExchangeRateEntity;
import com.currencyexchanger.command.CurrencyExchangeRate;
import java.util.Currency;

public class CurrencyExchangeRateMapper {

  private CurrencyExchangeRateMapper() {

  }

  public static CurrencyExchangeRate toDomain(ExchangeRateEntity entity) {
    return new CurrencyExchangeRate(
        Currency.getInstance(entity.getCurrencyCode()),
        entity.getBid(),
        entity.getAsk()
    );
  }
}
