package com.example.currencyexchanger.adapters.out.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class CurrencyAccountId implements Serializable {

    String clientId;
    String currency;
}
