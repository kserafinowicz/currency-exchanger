package com.example.currencyexchanger.adapters.out.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name="currency_account")
@IdClass(CurrencyAccountId.class)
@Getter
@Setter
public class CurrencyAccountEntity {

    @Id
    String id;

    @Id
    String currencyCode;

    BigDecimal amount;
}
