package com.currencyexchanger.app.adapter.in.web.command.exchange;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.currencyexchanger.app.adapters.in.web.command.exchange.ExchangeCurrencyController;
import com.currencyexchanger.command.CurrencyExchangeRate;
import com.currencyexchanger.command.events.AmountExchanged;
import com.currencyexchanger.command.ports.in.PerformCurrencyExchangeUseCase;
import com.currencyexchanger.command.ports.out.FindExchangeRatePort;
import com.currencyexchanger.common.errors.Error;
import io.vavr.collection.Array;
import io.vavr.control.Either;
import java.math.BigDecimal;
import java.util.Currency;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@WebMvcTest(ExchangeCurrencyController.class)
class ExchangeCurrencyControllerApiTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  PerformCurrencyExchangeUseCase performCurrencyExchangeUseCase;
  @MockBean
  FindExchangeRatePort findExchangeRatePort;

  @Test
  void shouldCorrectlyAcceptExchange() throws Exception {
    when(findExchangeRatePort.findExchangeRate(any())).thenReturn(
        Either.right(mockCurrencyExchangeRate()));
    when(performCurrencyExchangeUseCase.performExchange(any())).thenReturn(
        Either.right(new AmountExchanged(null)));

    RequestBuilder requestBuilder = post("/api/exchanges")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
              "accountId": "6a0368d5-4456-4372-b443-76995fb3a095",
              "fromCurrency": "PLN",
              "toCurrency": "USD",
              "amount": 123
            }
                        """);

    mockMvc.perform(requestBuilder).andExpect(status().isAccepted());
  }

  @Test
  void shouldReturnBadRequestWhenExchangeRateNotFound() throws Exception {
    when(findExchangeRatePort.findExchangeRate(any())).thenReturn(
        Either.left(new Error("Error")));

    RequestBuilder requestBuilder = post("/api/exchanges")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
              "accountId": "6a0368d5-4456-4372-b443-76995fb3a095",
              "fromCurrency": "PLN",
              "toCurrency": "USD",
              "amount": 123
            }
                        """);

    mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturnBadRequestWhenExchangeFails() throws Exception {
    when(findExchangeRatePort.findExchangeRate(any())).thenReturn(
        Either.right(mockCurrencyExchangeRate()));
    when(performCurrencyExchangeUseCase.performExchange(any())).thenReturn(
        Either.left(Array.of(new Error("Error"))));

    RequestBuilder requestBuilder = post("/api/exchanges")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
              "accountId": "6a0368d5-4456-4372-b443-76995fb3a095",
              "fromCurrency": "PLN",
              "toCurrency": "USD",
              "amount": 123
            }
                        """);

    mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
  }

  private CurrencyExchangeRate mockCurrencyExchangeRate() {
    return new CurrencyExchangeRate(Currency.getInstance("USD"), BigDecimal.TEN, BigDecimal.ONE);
  }
}
