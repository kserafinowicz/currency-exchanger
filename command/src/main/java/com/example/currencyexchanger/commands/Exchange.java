package com.example.currencyexchanger.commands;

import com.example.currencyexchanger.AccountId;
import com.example.currencyexchanger.CurrencyExchangeRate;

import java.math.BigDecimal;
import java.util.Currency;

public record Exchange(
        AccountId account,
        String fromCurrencyCode,
        String toCurrencyCode,
        BigDecimal amount,
        CurrencyExchangeRate exchangeRate,
        Currency baseCurrency) {}
