package com.currencyexchanger.app.adapters.in.web.query.account;

import com.currencyexchanger.query.UserAccountResult;
import com.currencyexchanger.query.ports.in.ShowUserAccountUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShowUserAccountController {

  private final ShowUserAccountUseCase showUserAccountUseCase;

  public ShowUserAccountController(ShowUserAccountUseCase showUserAccountUseCase) {
    this.showUserAccountUseCase = showUserAccountUseCase;
  }

  @GetMapping("/users/{accountId}")
  public ResponseEntity<UserAccountResult> showUserAccount(
      @PathVariable("accountId") String accountId) {
    return showUserAccountUseCase.showUserAccountResult(accountId)
        .fold(
            () -> ResponseEntity.notFound().build(),
            ResponseEntity::ok);
  }
}
