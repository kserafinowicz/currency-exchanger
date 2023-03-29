package com.example.currencyexchanger.commands;

import java.math.BigDecimal;
import java.util.Currency;


public record CreateUserAccount(String name, String surname, BigDecimal initialBalance, Currency baseCurrency) {
}
