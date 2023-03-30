package com.example.currencyexchanger.adapters.out.jpa.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.Data;

@Entity(name = "exchange_rate")
@Data
public class ExchangeRateEntity {

  @Id
  String currencyCode;
  String currency;

  @Column(scale = 4, precision = 20)
  BigDecimal ask;
  @Column(scale = 4, precision = 20)
  BigDecimal bid;
}
