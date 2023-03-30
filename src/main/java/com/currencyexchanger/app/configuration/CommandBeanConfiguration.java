package com.currencyexchanger.app.configuration;


import com.currencyexchanger.command.ports.in.CreateUserAccountUseCase;
import com.currencyexchanger.command.ports.out.SaveUserAccountPort;
import com.currencyexchanger.command.services.UserAccountCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CommandBeanConfiguration {

  private final SaveUserAccountPort saveUserAccountPort;

  @Bean
  public CreateUserAccountUseCase createUserAccountUseCase() {
    return new UserAccountCreator(saveUserAccountPort);
  }
}
