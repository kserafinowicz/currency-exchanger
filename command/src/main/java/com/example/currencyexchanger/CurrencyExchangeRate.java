package com.example.currencyexchanger;

import java.math.BigDecimal;
import java.util.Currency;

public record CurrencyExchangeRate(Currency currency, BigDecimal bid, BigDecimal ask) {

}
