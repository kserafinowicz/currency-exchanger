package com.example.currencyexchanger.adapters.in.web.command.account;

import com.example.currencyexchanger.adapters.in.web.command.account.dto.CreateUserAccountDto;
import com.example.currencyexchanger.commands.CreateUserAccount;
import com.example.currencyexchanger.ports.in.CreateUserAccountUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

@RestController
@RequestMapping("/api")
public class CreateUserAccountController {

    private final CreateUserAccountUseCase createUserAccountUseCase;
    private final Currency baseCurrency;

    public CreateUserAccountController(CreateUserAccountUseCase createUserAccountUseCase,
                                       @Value("${exchanger.base.currency}") String baseCurrency) {
        this.createUserAccountUseCase = createUserAccountUseCase;
        this.baseCurrency = Currency.getInstance(baseCurrency);
    }

    @PostMapping("/users")
    public ResponseEntity<String> createUserAccount(@RequestBody CreateUserAccountDto dto) {
        return createUserAccountUseCase.createUserAccount(new CreateUserAccount(dto.getName(), dto.getSurname(), dto.getInitialBalance(), baseCurrency)).fold(
                error -> ResponseEntity.badRequest().body(error.get().getResponseMessage()),
                result -> ResponseEntity.accepted().build()
        );
    }
}
