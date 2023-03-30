package com.currencyexchanger.command.ports.out;

import com.currencyexchanger.command.events.UserAccountCreated;

public interface SaveUserAccountPort {

    void save(UserAccountCreated userAccountCreated);
}
