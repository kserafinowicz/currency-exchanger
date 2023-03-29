package com.example.currencyexchanger.user;

import java.math.BigDecimal;

public record Account(String name, String surname, BigDecimal balance) {
}
