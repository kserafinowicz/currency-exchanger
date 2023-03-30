package com.currencyexchanger.app.configuration;


import com.currencyexchanger.command.ports.in.CreateUserAccountUseCase;
import com.currencyexchanger.command.ports.in.PerformCurrencyExchangeUseCase;
import com.currencyexchanger.command.ports.out.FindUserAccountPort;
import com.currencyexchanger.command.ports.out.SaveUserAccountPort;
import com.currencyexchanger.command.services.CurrencyExchangePerformer;
import com.currencyexchanger.command.services.UserAccountCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CommandBeanConfiguration {

  private final SaveUserAccountPort saveUserAccountPort;
  private final FindUserAccountPort findUserAccountPort;

  @Bean
  public CreateUserAccountUseCase createUserAccountUseCase() {
    return new UserAccountCreator(saveUserAccountPort);
  }

  @Bean
  public PerformCurrencyExchangeUseCase performCurrencyExchangeUseCase() {
    return new CurrencyExchangePerformer(findUserAccountPort, saveUserAccountPort);
  }
}
