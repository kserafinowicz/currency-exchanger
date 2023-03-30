package com.currencyexchanger.app.adapters.out.jpa.mappers;

import com.currencyexchanger.app.adapters.out.jpa.entities.CurrencyAccountEntity;
import com.currencyexchanger.app.adapters.out.jpa.entities.UserAccountEntity;
import com.currencyexchanger.command.AccountId;
import com.currencyexchanger.command.UserAccount;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserAccountMapper {

  private UserAccountMapper() {
  }

  public static UserAccountEntity mapToEntity(UserAccount userAccount) {
    UserAccountEntity entity = new UserAccountEntity();
    entity.setUserAccountId(userAccount.accountId().getUuid());
    entity.setName(userAccount.name());
    entity.setSurname(userAccount.surname());
    entity.setAccounts(mapToEntity(userAccount.accountId().getUuid(), userAccount.accounts()));
    return entity;
  }

  public static UserAccount mapToDomain(UserAccountEntity entity) {
    return new UserAccount(
        AccountId.fromString(entity.getUserAccountId()).get(),
        entity.getName(),
        entity.getSurname(),
        mapToDomain(entity.getAccounts()));
  }

  private static Set<CurrencyAccountEntity> mapToEntity(String accountId,
      Map<String, BigDecimal> accounts) {
    Set<CurrencyAccountEntity> result = new HashSet<>();
    accounts.keySet().forEach(key -> {
      var entity = new CurrencyAccountEntity();
      entity.setAccountId(accountId);
      entity.setCurrencyCode(key);
      entity.setAmount(accounts.get(key));
      result.add(entity);
    });
    return result;
  }

  private static Map<String, BigDecimal> mapToDomain(Set<CurrencyAccountEntity> entities) {
    Map<String, BigDecimal> result = new HashMap<>();
    entities.forEach(currencyAccountEntity -> result.put(currencyAccountEntity.getCurrencyCode(),
        currencyAccountEntity.getAmount()));
    return result;
  }
}
