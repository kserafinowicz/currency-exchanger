package com.example.currencyexchanger.adapters.out.jpa;

import com.example.currencyexchanger.adapters.out.jpa.repository.UserAccountRepository;
import com.example.currencyexchanger.events.UserAccountCreated;
import com.example.currencyexchanger.ports.out.SaveUserAccountPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.currencyexchanger.adapters.out.jpa.mappers.UserAccountMapper.mapToEntity;

@Component
@RequiredArgsConstructor
public class SaveUserAccountAdapter implements SaveUserAccountPort {

    private final UserAccountRepository userAccountRepository;

    @Transactional
    @Override
    public void save(UserAccountCreated userAccountCreated) {
        userAccountRepository.save(mapToEntity(userAccountCreated.userAccount()));
    }
}
