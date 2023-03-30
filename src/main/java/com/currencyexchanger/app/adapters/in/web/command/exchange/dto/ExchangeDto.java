package com.currencyexchanger.app.adapters.in.web.command.exchange.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeDto {

  String accountId;
  String fromCurrency;
  String toCurrency;
  BigDecimal amount;

}
