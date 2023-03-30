package com.example.currencyexchanger.adapters.out.jpa.mappers;

import com.example.currencyexchanger.UserAccount;
import com.example.currencyexchanger.adapters.out.jpa.entities.CurrencyAccountEntity;
import com.example.currencyexchanger.adapters.out.jpa.entities.UserAccountEntity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class UserAccountMapper {

    public static UserAccountEntity mapToEntity(UserAccount userAccount) {
        UserAccountEntity entity = new UserAccountEntity();
        entity.setUserAccountId(userAccount.accountId().getUuid());
        entity.setName(userAccount.name());
        entity.setSurname(userAccount.surname());
        entity.setAccounts(mapToEntity(userAccount.accountId().getUuid(), userAccount.accounts()));
        return entity;
    }

    private static Set<CurrencyAccountEntity> mapToEntity(String accountId, Map<String, BigDecimal> accounts) {
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
}
