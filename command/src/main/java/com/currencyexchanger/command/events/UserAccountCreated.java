package com.currencyexchanger.command.events;

import com.currencyexchanger.command.UserAccount;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAccountCreated implements UserAccountEvent<UserAccount> {

  UserAccount event;

  @Override
  public UserAccount get() {
    return event;
  }
}
