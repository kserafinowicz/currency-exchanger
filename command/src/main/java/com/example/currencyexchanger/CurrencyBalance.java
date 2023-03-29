package com.example.currencyexchanger;

import java.math.BigDecimal;
import java.util.Currency;

record CurrencyBalance(Currency currency, BigDecimal balance) {
}