package com.example.currencyexchanger.configuration;


import com.example.currencyexchanger.ports.in.CreateUserAccountUseCase;
import com.example.currencyexchanger.ports.out.SaveUserAccountPort;
import com.example.currencyexchanger.services.UserAccountCreator;
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
