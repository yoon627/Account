package com.yoon.account.service;

import com.yoon.account.domain.Account;
import com.yoon.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  public Account createAccount() {
    return accountRepository.save(new Account());
  }

  public void deleteAccount(long id) {
    accountRepository.deleteById(id);
  }
}
