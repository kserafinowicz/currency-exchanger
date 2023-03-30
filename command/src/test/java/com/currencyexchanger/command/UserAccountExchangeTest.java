package com.currencyexchanger.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.currencyexchanger.command.commands.Exchange;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserAccountExchangeTest {

  @Test
  void shouldCorrectlyExchangeCurrencyFromPlnToUsdWhenCommandIsValid() {
    // given
    var currencyExchange = new CurrencyExchangeRate(
        Currency.getInstance("USD"),
        new BigDecimal("2"),
        new BigDecimal("3")
    );

    var accountId = AccountId.fromString(UUID.randomUUID().toString()).get();

    Exchange command = new Exchange(
        accountId,
        "PLN",
        "USD",
        BigDecimal.valueOf(100L),
        currencyExchange,
        Currency.getInstance("PLN"),
        Set.of("PLN", "USD")
    );

    UserAccount account = new UserAccount(accountId, "John", "Smith",
        Map.of("PLN", BigDecimal.valueOf(2000L)));

    // when
    var result = account.apply(command);

    // then
    assertTrue(result.isRight());
    assertEquals(accountId, result.get().get().accountId());
    assertEquals("John", result.get().get().name());
    assertEquals("Smith", result.get().get().surname());
    assertEquals(2, result.get().get().accounts().values().size());
    assertEquals(BigDecimal.valueOf(1900L), result.get().get().accounts().get("PLN"));
    assertEquals(BigDecimal.valueOf(200L), result.get().get().accounts().get("USD"));
  }

  @Test
  void shouldCorrectlyExchangeCurrencyFromUsdToPlnWhenCommandIsValid() {
    // given
    var currencyExchange = new CurrencyExchangeRate(
        Currency.getInstance("USD"),
        new BigDecimal("2"),
        new BigDecimal("4")
    );

    var accountId = AccountId.fromString(UUID.randomUUID().toString()).get();

    Exchange command = new Exchange(
        accountId,
        "USD",
        "PLN",
        BigDecimal.valueOf(100L),
        currencyExchange,
        Currency.getInstance("PLN"),
        Set.of("PLN", "USD")
    );

    UserAccount account = new UserAccount(accountId, "John", "Smith",
        Map.of("PLN", BigDecimal.valueOf(2000L), "USD", BigDecimal.valueOf(200L)));

    // when
    var result = account.apply(command);

    // then
    assertTrue(result.isRight());
    assertEquals(accountId, result.get().get().accountId());
    assertEquals("John", result.get().get().name());
    assertEquals("Smith", result.get().get().surname());
    assertEquals(2, result.get().get().accounts().values().size());
    assertEquals(BigDecimal.valueOf(2025L), result.get().get().accounts().get("PLN"));
    assertEquals(BigDecimal.valueOf(100L), result.get().get().accounts().get("USD"));
  }

  @Test
  void shouldReturnErrorWhenNoCurrencyAccount() {
    // given
    var currencyExchange = new CurrencyExchangeRate(
        Currency.getInstance("USD"),
        new BigDecimal("2"),
        new BigDecimal("4")
    );

    var accountId = AccountId.fromString(UUID.randomUUID().toString()).get();

    Exchange command = new Exchange(
        accountId,
        "USD",
        "PLN",
        BigDecimal.valueOf(100L),
        currencyExchange,
        Currency.getInstance("PLN"),
        Set.of("PLN", "USD")
    );

    UserAccount account = new UserAccount(accountId, "John", "Smith",
        Map.of("PLN", BigDecimal.valueOf(2000L)));

    // when
    var result = account.apply(command);

    // then
    assertTrue(result.isLeft());
  }

  @Test
  void shouldReturnErrorWhenAmountNegative() {
    // given
    var currencyExchange = new CurrencyExchangeRate(
        Currency.getInstance("USD"),
        new BigDecimal("2"),
        new BigDecimal("4")
    );

    var accountId = AccountId.fromString(UUID.randomUUID().toString()).get();

    Exchange command = new Exchange(
        accountId,
        "PLN",
        "USD",
        BigDecimal.valueOf(-100L),
        currencyExchange,
        Currency.getInstance("PLN"),
        Set.of("PLN", "USD")
    );

    UserAccount account = new UserAccount(accountId, "John", "Smith",
        Map.of("PLN", BigDecimal.valueOf(2000L)));

    // when
    var result = account.apply(command);

    // then
    assertTrue(result.isLeft());
  }

  @Test
  void shouldReturnErrorWhenNotEnoughFunds() {
    // given
    var currencyExchange = new CurrencyExchangeRate(
        Currency.getInstance("USD"),
        new BigDecimal("2"),
        new BigDecimal("4")
    );

    var accountId = AccountId.fromString(UUID.randomUUID().toString()).get();

    Exchange command = new Exchange(
        accountId,
        "PLN",
        "USD",
        BigDecimal.valueOf(1000L),
        currencyExchange,
        Currency.getInstance("PLN"),
        Set.of("PLN", "USD")
    );

    UserAccount account = new UserAccount(accountId, "John", "Smith",
        Map.of("PLN", BigDecimal.valueOf(10L)));

    // when
    var result = account.apply(command);

    // then
    assertTrue(result.isLeft());
  }

  @Test
  void shouldReturnErrorWhenCurrenciesAreTheSame() {
    // given
    var currencyExchange = new CurrencyExchangeRate(
        Currency.getInstance("USD"),
        new BigDecimal("2"),
        new BigDecimal("4")
    );

    var accountId = AccountId.fromString(UUID.randomUUID().toString()).get();

    Exchange command = new Exchange(
        accountId,
        "PLN",
        "PLN",
        BigDecimal.valueOf(10L),
        currencyExchange,
        Currency.getInstance("PLN"),
        Set.of("PLN", "USD")
    );

    UserAccount account = new UserAccount(accountId, "John", "Smith",
        Map.of("PLN", BigDecimal.valueOf(100L)));

    // when
    var result = account.apply(command);

    // then
    assertTrue(result.isLeft());
  }

  @Test
  void shouldReturnErrorWhenCurrenciesNotOnAllowedList() {
    // given
    var currencyExchange = new CurrencyExchangeRate(
        Currency.getInstance("USD"),
        new BigDecimal("2"),
        new BigDecimal("4")
    );

    var accountId = AccountId.fromString(UUID.randomUUID().toString()).get();

    Exchange command = new Exchange(
        accountId,
        "PLN",
        "CHF",
        BigDecimal.valueOf(10L),
        currencyExchange,
        Currency.getInstance("PLN"),
        Set.of("PLN", "USD")
    );

    UserAccount account = new UserAccount(accountId, "John", "Smith",
        Map.of("PLN", BigDecimal.valueOf(100L)));

    // when
    var result = account.apply(command);

    // then
    assertTrue(result.isLeft());
  }
}
