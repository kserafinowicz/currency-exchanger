package com.example.currencyexchanger.adapters.out.jpa;

import com.example.currencyexchanger.adapters.out.jpa.repository.UserAccountRepository;
import com.example.currencyexchanger.events.UserAccountCreated;
import com.example.currencyexchanger.ports.out.SaveUserAccountPort;
import lombok.RequiredArgsConstructor;

import static com.example.currencyexchanger.adapters.out.jpa.mappers.UserAccountMapper.mapToEntity;

@RequiredArgsConstructor
public class SaveUserAccountAdapter implements SaveUserAccountPort {

    private final UserAccountRepository userAccountRepository;

    @Override
    public void save(UserAccountCreated userAccountCreated) {
        userAccountRepository.save(mapToEntity(userAccountCreated.userAccount()));
    }
}
