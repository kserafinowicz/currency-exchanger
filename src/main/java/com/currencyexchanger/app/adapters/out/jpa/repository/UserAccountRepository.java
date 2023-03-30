package com.currencyexchanger.app.adapters.out.jpa.repository;

import com.currencyexchanger.app.adapters.out.jpa.entities.UserAccountEntity;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

public interface UserAccountRepository extends Repository<UserAccountEntity, String> {

  Option<UserAccountEntity> findByUserAccountId(String accountId);

  void save(UserAccountEntity entity);
}
