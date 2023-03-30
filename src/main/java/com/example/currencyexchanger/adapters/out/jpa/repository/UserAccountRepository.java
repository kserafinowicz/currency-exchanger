package com.example.currencyexchanger.adapters.out.jpa.repository;

import com.example.currencyexchanger.adapters.out.jpa.entities.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, String> {
}
