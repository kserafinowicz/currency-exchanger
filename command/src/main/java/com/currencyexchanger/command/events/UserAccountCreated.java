package com.currencyexchanger.command.events;

import com.currencyexchanger.command.UserAccount;

public record UserAccountCreated(UserAccount event) implements UserAccountEvent<UserAccount> {

  @Override
  public UserAccount get() {
    return event;
  }
}
