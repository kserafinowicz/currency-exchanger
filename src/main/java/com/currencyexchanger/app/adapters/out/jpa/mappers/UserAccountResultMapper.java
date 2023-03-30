package com.currencyexchanger.app.adapters.out.jpa.mappers;

import com.currencyexchanger.app.adapters.out.jpa.entities.CurrencyAccountEntity;
import com.currencyexchanger.app.adapters.out.jpa.entities.UserAccountEntity;
import com.currencyexchanger.command.AccountId;
import com.currencyexchanger.query.UserAccountResult;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserAccountResultMapper {

  private UserAccountResultMapper() {
  }

  public static UserAccountResult mapToDomainResult(UserAccountEntity entity) {
    return new UserAccountResult(
        AccountId.fromString(entity.getUserAccountId()).get().getUuid(),
        entity.getName(),
        entity.getSurname(),
        mapToDomain(entity.getAccounts()));
  }

  private static Map<String, BigDecimal> mapToDomain(Set<CurrencyAccountEntity> entities) {
    Map<String, BigDecimal> result = new HashMap<>();
    entities.forEach(currencyAccountEntity -> result.put(currencyAccountEntity.getCurrencyCode(),
        currencyAccountEntity.getAmount()));
    return result;
  }
}
