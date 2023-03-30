package com.example.currencyexchanger.ports.out;

import com.example.currencyexchanger.events.UserAccountCreated;

public interface SaveUserAccountPort {

    void save(UserAccountCreated userAccountCreated);
}
