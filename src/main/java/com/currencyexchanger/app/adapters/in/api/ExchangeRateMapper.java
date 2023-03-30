package com.currencyexchanger.app.adapters.in.api;

import com.currencyexchanger.app.adapters.out.jpa.entities.ExchangeRateEntity;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.experimental.UtilityClass;

@UtilityClass
class ExchangeRateMapper {

  public ExchangeRateEntity toEntity(RateResponseDto dto) {
    ExchangeRateEntity entity = new ExchangeRateEntity();
    entity.setCurrencyCode(dto.code);
    entity.setCurrency(dto.currency);
    entity.setAsk(dto.getAsk().setScale(4, RoundingMode.HALF_UP));
    entity.setBid(BigDecimal.ONE.setScale(4, RoundingMode.HALF_UP)
        .divide(dto.getBid().setScale(4, RoundingMode.HALF_UP), RoundingMode.HALF_UP));
    return entity;
  }
}
