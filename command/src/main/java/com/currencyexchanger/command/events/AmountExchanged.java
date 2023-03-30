package com.currencyexchanger.command.events;

import com.currencyexchanger.command.UserAccount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AmountExchanged implements UserAccountEvent<UserAccount> {

  private final UserAccount event;

  @Override
  public UserAccount get() {
    return event;
  }
}
