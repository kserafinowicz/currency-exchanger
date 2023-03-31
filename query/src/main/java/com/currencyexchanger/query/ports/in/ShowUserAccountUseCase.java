package com.currencyexchanger.query.ports.in;

import com.currencyexchanger.query.UserAccountResult;
import io.vavr.control.Option;

public interface ShowUserAccountUseCase {

  Option<UserAccountResult> showUserAccountResult(String accountId);
}
