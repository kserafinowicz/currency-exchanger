package com.currencyexchanger.query;

import java.math.BigDecimal;
import java.util.Map;

public record UserAccountResult(String accountId, String name, String surname, Map<String, BigDecimal> accounts) {
}
