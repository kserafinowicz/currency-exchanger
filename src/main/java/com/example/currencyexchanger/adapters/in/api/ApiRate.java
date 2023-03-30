package com.example.currencyexchanger.adapters.in.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class ApiRate implements Serializable {
  String no;
  LocalDate effectiveDate;
  BigDecimal bid;
  BigDecimal ask;
}
