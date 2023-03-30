package com.currencyexchanger.command.commands;

import com.currencyexchanger.command.AccountId;
import com.currencyexchanger.command.CurrencyExchangeRate;

import java.math.BigDecimal;
import java.util.Currency;

public record Exchange(
        AccountId account,
        String fromCurrencyCode,
        String toCurrencyCode,
        BigDecimal amount,
        CurrencyExchangeRate exchangeRate,
        Currency baseCurrency) {}
