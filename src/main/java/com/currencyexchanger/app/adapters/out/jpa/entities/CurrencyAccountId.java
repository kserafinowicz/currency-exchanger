package com.currencyexchanger.app.adapters.out.jpa.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class CurrencyAccountId implements Serializable {

    String accountId;
    String currencyCode;
}
