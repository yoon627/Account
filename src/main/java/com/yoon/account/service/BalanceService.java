package com.yoon.account.service;

import com.yoon.account.domain.Account;
import com.yoon.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BalanceService {

  private final AccountRepository accountRepository;

  public long getBalance(long id) {
    Account account = accountRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Account not found"));
    return account.getBalance();
  }

  @Transactional
  public long deposit(long id, long amount) {
    Account account = accountRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Account not found"));
    account.deposit(amount);
    return account.getBalance();
  }

  @Transactional
  public long withdraw(long id, long amount) {
    Account account = accountRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Account not found"));
    account.withdraw(amount);
    return account.getBalance();
  }
}
