package com.currencyexchanger.app.adapters.out.jpa.repository;

import com.currencyexchanger.app.adapters.out.jpa.entities.ExchangeRateEntity;
import io.vavr.control.Option;
import org.springframework.data.repository.Repository;

public interface ExchangeRateRepository extends Repository<ExchangeRateEntity, String> {

  Option<ExchangeRateEntity> findByCurrencyCode(String currencyId);

  void save(ExchangeRateEntity entity);
}
