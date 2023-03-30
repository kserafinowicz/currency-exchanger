package com.currencyexchanger.query.ports.in;

import com.currencyexchanger.query.UserAccountResult;

public interface ShowUserAccountUseCase {

  UserAccountResult showUser(String id);
}
