package com.currencyexchanger.app.adapters.in.web.command.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateUserAccountDto {
    String name;
    String surname;
    BigDecimal initialBalance;
}
