package com.yoon.account.controller;

import com.yoon.account.domain.Account;
import com.yoon.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

  private final AccountService accountService;

  @PostMapping
  public Account createAccount() {
    return accountService.createAccount();
  }

  @DeleteMapping("{id}")
  public void deleteAccount(@PathVariable long id) {
    accountService.deleteAccount(id);
  }
}
