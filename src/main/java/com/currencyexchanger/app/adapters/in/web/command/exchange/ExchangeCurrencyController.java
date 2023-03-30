package com.currencyexchanger.app.adapters.in.web.command.exchange;

import com.currencyexchanger.app.adapters.in.web.command.exchange.dto.ExchangeDto;
import com.currencyexchanger.command.AccountId;
import com.currencyexchanger.command.CurrencyExchangeRate;
import com.currencyexchanger.command.commands.Exchange;
import com.currencyexchanger.command.ports.in.PerformCurrencyExchangeUseCase;
import com.currencyexchanger.command.ports.out.FindExchangeRatePort;
import java.util.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExchangeCurrencyController {

  private final PerformCurrencyExchangeUseCase performCurrencyExchangeUseCase;
  private final FindExchangeRatePort findExchangeRatePort;
  private final Currency baseCurrency;

  public ExchangeCurrencyController(PerformCurrencyExchangeUseCase performCurrencyExchangeUseCase,
      FindExchangeRatePort findExchangeRatePort,
      @Value("${exchanger.base.currency}") String baseCurrency) {
    this.performCurrencyExchangeUseCase = performCurrencyExchangeUseCase;
    this.findExchangeRatePort = findExchangeRatePort;
    this.baseCurrency = Currency.getInstance(baseCurrency);
  }

  @PostMapping("/exchanges")
  public ResponseEntity<String> performExchange(ExchangeDto exchangeDto) {
    return
        findExchangeRatePort.findExchangeRate(getExchangeRateCurrency(exchangeDto, baseCurrency))
            .fold(
                error -> ResponseEntity.badRequest().body(error.getResponseMessage()),
                exchangeRate -> performCurrencyExchangeUseCase.performExchange(
                        assembleCommand(exchangeDto, exchangeRate, baseCurrency)
                    )
                    .fold(
                        error -> ResponseEntity.badRequest().body(error.get().getResponseMessage()),
                        result -> ResponseEntity.accepted().build()
                    )
            );
  }

  private Exchange assembleCommand(ExchangeDto exchangeDto, CurrencyExchangeRate rate,
      Currency baseCurrency) {
    return new Exchange(
        AccountId.fromString(exchangeDto.getAccountId()).get(),
        exchangeDto.getFromCurrency(),
        exchangeDto.getToCurrency(),
        exchangeDto.getAmount(),
        rate,
        baseCurrency
    );
  }

  private String getExchangeRateCurrency(ExchangeDto exchangeDto, Currency baseCurrency) {
    return baseCurrency.getCurrencyCode().equals(exchangeDto.getFromCurrency())
        ? exchangeDto.getToCurrency()
        : exchangeDto.getFromCurrency();
  }
}
