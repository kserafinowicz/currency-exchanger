package com.currencyexchanger.command.ports.out;

import com.currencyexchanger.command.UserAccount;
import io.vavr.control.Option;

public interface FindUserAccountPort {

  Option<UserAccount> findUserAccount(String accountId);
}
