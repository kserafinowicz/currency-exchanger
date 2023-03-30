package com.currencyexchanger.command.ports.out;

import com.currencyexchanger.command.UserAccount;
import com.currencyexchanger.command.events.UserAccountEvent;

public interface SaveUserAccountPort {

    void save(UserAccountEvent<? extends UserAccount> event);
}
