package com.currencyexchanger.command.events;

import com.currencyexchanger.command.UserAccount;

public record AmountExchanged(UserAccount event) implements UserAccountEvent<UserAccount> {

  @Override
  public UserAccount get() {
    return event;
  }
}
