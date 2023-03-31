package com.currencyexchanger.query.services;

import com.currencyexchanger.query.UserAccountResult;
import com.currencyexchanger.query.ports.in.ShowUserAccountUseCase;
import com.currencyexchanger.query.ports.out.FindUserAccountResultPort;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAccountPresenter implements ShowUserAccountUseCase {

  private final FindUserAccountResultPort findUserAccountResultPort;

  @Override
  public Option<UserAccountResult> showUserAccountResult(String accountId) {
    return findUserAccountResultPort.findUserAccountResult(accountId);
  }
}
