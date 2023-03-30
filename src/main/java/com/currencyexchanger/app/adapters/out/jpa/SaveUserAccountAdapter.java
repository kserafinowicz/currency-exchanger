package com.currencyexchanger.app.adapters.out.jpa;

import com.currencyexchanger.app.adapters.out.jpa.repository.UserAccountRepository;
import com.currencyexchanger.command.events.UserAccountCreated;
import com.currencyexchanger.command.ports.out.SaveUserAccountPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.currencyexchanger.app.adapters.out.jpa.mappers.UserAccountMapper.mapToEntity;

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
