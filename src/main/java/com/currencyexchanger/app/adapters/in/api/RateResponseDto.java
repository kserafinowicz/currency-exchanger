package com.currencyexchanger.app.adapters.in.api;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class RateResponseDto implements Serializable {
  String table;
  String currency;
  String code;
  @JsonAlias("rates")
  List<ApiRate> apiRates;

  public BigDecimal getAsk() {
    return apiRates.get(0).getAsk();
  }

  public BigDecimal getBid() {
    return apiRates.get(0).getBid();
  }
}