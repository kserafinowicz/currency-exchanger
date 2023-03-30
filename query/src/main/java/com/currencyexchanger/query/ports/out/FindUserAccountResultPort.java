package com.currencyexchanger.query.ports.out;

import com.currencyexchanger.query.UserAccountResult;
import io.vavr.control.Option;

public interface FindUserAccountResultPort {

  Option<UserAccountResult> findUserAccountResult(String accountId);
}
