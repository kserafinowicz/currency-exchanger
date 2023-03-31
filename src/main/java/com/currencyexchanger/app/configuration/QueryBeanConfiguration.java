package com.currencyexchanger.app.configuration;

import com.currencyexchanger.query.ports.in.ShowUserAccountUseCase;
import com.currencyexchanger.query.ports.out.FindUserAccountResultPort;
import com.currencyexchanger.query.services.UserAccountPresenter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QueryBeanConfiguration {

  private final FindUserAccountResultPort findUserAccountResultPort;

  @Bean
  public ShowUserAccountUseCase showUserAccountUseCase() {
    return new UserAccountPresenter(findUserAccountResultPort);
  }
}
