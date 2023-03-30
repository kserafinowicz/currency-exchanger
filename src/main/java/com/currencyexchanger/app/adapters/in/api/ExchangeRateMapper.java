package com.currencyexchanger.app.adapters.in.api;

import com.currencyexchanger.app.adapters.out.jpa.entities.ExchangeRateEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
class ExchangeRateMapper {

  public ExchangeRateEntity toEntity(RateResponseDto dto) {
    ExchangeRateEntity entity = new ExchangeRateEntity();
    entity.setCurrencyCode(dto.code);
    entity.setCurrency(dto.currency);
    entity.setAsk(dto.getAsk());
    entity.setBid(dto.getBid());
    return entity;
  }
}
