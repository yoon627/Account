package com.yoon.account.controller;

import com.yoon.account.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class BalanceController {

  private final BalanceService balanceService;

  @GetMapping("/{id}/balance")
  public long getBalance(@PathVariable("id") long id) {
    return balanceService.getBalance(id);
  }

  @PostMapping("/{id}/deposit")
  public long deposit(@PathVariable("id") long id, @RequestBody long amount) {
    return balanceService.deposit(id, amount);
  }

  @PostMapping("/{id}/withdraw")
  public long withdraw(@PathVariable("id") long id, @RequestBody long amount) {
    return balanceService.withdraw(id, amount);
  }

}
