package com.currencyexchanger.app.adapters.out.jpa.repository;

import com.currencyexchanger.app.adapters.out.jpa.entities.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, String> {

}
