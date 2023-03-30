package com.currencyexchanger.command;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.currencyexchanger.command.commands.CreateUserAccount;
import java.math.BigDecimal;
import java.util.Currency;
import org.junit.jupiter.api.Test;

class UserAccountCreationTest {

  @Test
  void shouldCreateUserAccountWhenCommandIsValid() {
    // given
    CreateUserAccount command = new CreateUserAccount("John", "Smith", BigDecimal.ONE,
        Currency.getInstance("PLN"));

    // when
    var result = UserAccount.apply(command);

    // then
    assertTrue(result.isRight());
    assertNotNull(result.get().get().accountId());
    assertEquals("John", result.get().get().name());
    assertEquals("Smith", result.get().get().surname());
    assertEquals(1, result.get().get().accounts().values().size());
    assertEquals(BigDecimal.ONE, result.get().get().accounts().get("PLN"));
  }

  @Test
  void shouldReturnErrorWhenNameIsEmpty() {
    // given
    CreateUserAccount command = new CreateUserAccount("", "Smith", BigDecimal.ONE,
        Currency.getInstance("PLN"));

    // when
    var result = UserAccount.apply(command);

    // then
    assertTrue(result.isLeft());
  }

  @Test
  void shouldReturnErrorWhenSurnameIsEmpty() {
    // given
    CreateUserAccount command = new CreateUserAccount("John", "", BigDecimal.ONE,
        Currency.getInstance("PLN"));

    // when
    var result = UserAccount.apply(command);

    // then
    assertTrue(result.isLeft());
  }

  @Test
  void shouldReturnErrorWhenBalanceIsNull() {
    // given
    CreateUserAccount command = new CreateUserAccount("John", "Smith", null,
        Currency.getInstance("PLN"));

    // when
    var result = UserAccount.apply(command);

    // then
    assertTrue(result.isLeft());
  }
  @Test
  void shouldReturnErrorWhenBalanceIsNegative() {
    // given
    CreateUserAccount command = new CreateUserAccount("John", "Smith", BigDecimal.valueOf(-1),
        Currency.getInstance("PLN"));

    // when
    var result = UserAccount.apply(command);

    // then
    assertTrue(result.isLeft());
  }
}
