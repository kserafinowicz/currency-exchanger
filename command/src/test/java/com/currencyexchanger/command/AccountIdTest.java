package com.currencyexchanger.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountIdTest {

  @Test
  void shouldReturnIdWhenUuidValid() {
    // when
    var result = AccountId.fromString("6a0368d5-4456-4372-b443-76995fb3a095");

    // then
    Assertions.assertTrue(result.isRight());
  }

  @Test
  void shouldReturnErrorWhenUuidNull() {
    // when
    var result = AccountId.fromString(null);

    // then
    Assertions.assertTrue(result.isLeft());
  }
}
