package com.currencyexchanger.app.adapters.out.jpa;

import static com.currencyexchanger.app.adapters.out.jpa.mappers.UserAccountMapper.mapToEntity;

import com.currencyexchanger.app.adapters.out.jpa.repository.UserAccountRepository;
import com.currencyexchanger.command.UserAccount;
import com.currencyexchanger.command.events.UserAccountEvent;
import com.currencyexchanger.command.ports.out.SaveUserAccountPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveUserAccountAdapter implements SaveUserAccountPort {

    private final UserAccountRepository userAccountRepository;

    @Transactional
    @Override
    public void save(UserAccountEvent<? extends UserAccount> userAccountEvent) {
        userAccountRepository.save(mapToEntity(userAccountEvent.get()));
    }
}
