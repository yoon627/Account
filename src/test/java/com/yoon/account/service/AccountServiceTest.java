package com.yoon.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.yoon.account.domain.Account;
import com.yoon.account.repository.AccountRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private AccountService accountService;

  @Test
  @DisplayName("계좌 생성 성공 테스트")
  void createAccountSuccess() {
    // given
    Account newAccount = Account.builder().id(1L).build();
    when(accountRepository.save(any(Account.class))).thenReturn(newAccount);

    // when
    Account result = accountService.createAccount();

    // then
    verify(accountRepository).save(any(Account.class));
    assertEquals(result.getId(), 1L);
  }

  @Test
  @DisplayName("계좌 삭제 성공 테스트")
  void deleteAccountSuccess() {
    // given
    long accountId = 1L;

    // when
    accountService.deleteAccount(accountId);

    // then
    verify(accountRepository).deleteById(accountId);
  }
}