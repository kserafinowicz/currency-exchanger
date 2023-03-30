package com.example.currencyexchanger.adapters.out.jpa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity(name="user_account")
@Setter
@Getter
public class UserAccountEntity {

    @Id
    String userAccountId;
    String name;
    String surname;

    @OneToMany(cascade = CascadeType.ALL)
    Set<CurrencyAccountEntity> accounts;
}
