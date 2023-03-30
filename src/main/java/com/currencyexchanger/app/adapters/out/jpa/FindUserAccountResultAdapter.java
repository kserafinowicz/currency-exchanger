package com.currencyexchanger.app.adapters.out.jpa;

import com.currencyexchanger.app.adapters.out.jpa.mappers.UserAccountMapper;
import com.currencyexchanger.app.adapters.out.jpa.mappers.UserAccountResultMapper;
import com.currencyexchanger.app.adapters.out.jpa.repository.UserAccountRepository;
import com.currencyexchanger.command.UserAccount;
import com.currencyexchanger.command.ports.out.FindUserAccountPort;
import com.currencyexchanger.query.UserAccountResult;
import com.currencyexchanger.query.ports.out.FindUserAccountResultPort;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindUserAccountResultAdapter implements FindUserAccountResultPort,
    FindUserAccountPort {

  private final UserAccountRepository userAccountRepository;

  public Option<UserAccount> findUserAccount(String accountId) {
    return userAccountRepository.findByUserAccountId(accountId).map(UserAccountMapper::mapToDomain);
  }

  public Option<UserAccountResult> findUserAccountResult(String accountId) {
    return userAccountRepository.findByUserAccountId(accountId).map(UserAccountResultMapper::mapToDomainResult);
  }
}
