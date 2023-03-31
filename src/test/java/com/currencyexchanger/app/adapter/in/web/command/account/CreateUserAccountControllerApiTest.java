package com.currencyexchanger.app.adapter.in.web.command.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.currencyexchanger.app.adapters.in.web.command.account.CreateUserAccountController;
import com.currencyexchanger.command.events.UserAccountCreated;
import com.currencyexchanger.command.ports.in.CreateUserAccountUseCase;
import com.currencyexchanger.common.errors.Error;
import io.vavr.collection.Array;
import io.vavr.control.Either;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

@WebMvcTest(CreateUserAccountController.class)
class CreateUserAccountControllerApiTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CreateUserAccountUseCase createUserAccountUseCase;

  @Test
  void shouldAcceptUserCreation() throws Exception {
    Mockito.when(createUserAccountUseCase.createUserAccount(Mockito.any())).thenReturn(
        Either.right(new UserAccountCreated(null)));

    RequestBuilder requestBuilder = post("/api/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("""
            {
              "name": "John",
              "surname": "Smith",
              "initialBalance": 12345.67
            }
            """);

    mockMvc.perform(requestBuilder).andExpect(status().isAccepted());
  }

  @Test
  void shouldReturnBadRequestErrorWhenCreationFails() throws Exception {
    Mockito.when(createUserAccountUseCase.createUserAccount(Mockito.any())).thenReturn(
        Either.left(Array.of(new Error("Error"))));

    RequestBuilder requestBuilder = post("/api/users")
        .contentType(MediaType.APPLICATION_JSON);

    mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
  }

}
