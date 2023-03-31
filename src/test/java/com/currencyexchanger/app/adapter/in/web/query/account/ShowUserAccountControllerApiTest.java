package com.currencyexchanger.app.adapter.in.web.query.account;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.currencyexchanger.app.adapters.in.web.query.account.ShowUserAccountController;
import com.currencyexchanger.query.UserAccountResult;
import com.currencyexchanger.query.ports.in.ShowUserAccountUseCase;
import io.vavr.control.Option;
import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ShowUserAccountController.class)
class ShowUserAccountControllerApiTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ShowUserAccountUseCase showUserAccountUseCase;

  @Test
  void shouldCorrectlyReturnExistingUser() throws Exception {
    when(showUserAccountUseCase.showUserAccountResult(anyString())).thenReturn(
        Option.of(new UserAccountResult("account-id", "John", "Smith",
            Map.of("USD", new BigDecimal("2000.23")))));

    mockMvc.perform(get("/api/users/account-id"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.accountId", is("account-id")))
        .andExpect(jsonPath("$.name", is("John")))
        .andExpect(jsonPath("$.surname", is("Smith")))
        .andExpect(jsonPath("$.accounts.USD", is(2000.23)))
    ;
  }

  @Test
  void shouldReturnNotFound() throws Exception {
    when(showUserAccountUseCase.showUserAccountResult(anyString())).thenReturn(Option.none());

    mockMvc.perform(get("/api/users/account-id"))
        .andExpect(status().isNotFound());
  }
}
