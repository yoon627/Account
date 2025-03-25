package com.yoon.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.yoon.account.domain.Account;
import com.yoon.account.repository.AccountRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BalanceServiceTest {

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private BalanceService balanceService;

  @Test
  @DisplayName("계좌 조회 성공 테스트")
  void getBalanceSuccess() {
    // given
    Account account = Account.builder()
        .id(1L)
        .balance(1000L)
        .build();
    when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

    // when
    long balance = balanceService.getBalance(1L);

    // then
    assertEquals(1000L, balance);
    verify(accountRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("계좌 입금 성공 테스트")
  void depositSuccess() {
    // given
    Account account = spy(Account.builder()
        .id(1L)
        .balance(1000L)
        .build());
    when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

    // when
    long updatedBalance = balanceService.deposit(1L, 500L);

    // then
    assertEquals(1500L, updatedBalance);
    verify(account, times(1)).deposit(500L);
    verify(accountRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("계좌 출금 성공 테스트")
  void withdrawSuccess() {
    // given
    Account account = spy(Account.builder()
        .id(1L)
        .balance(1000L)
        .build());
    when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

    // when
    long updatedBalance = balanceService.withdraw(1L, 500L);

    // then
    assertEquals(500L, updatedBalance);
    verify(account, times(1)).withdraw(500L);
    verify(accountRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("계좌가 존재하지 않을 때 getBalance() 호출 시 예외 발생")
  void getBalanceAccountNotFound() {
    // given
    when(accountRepository.findById(1L)).thenReturn(Optional.empty());

    // when & then
    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> balanceService.getBalance(1L));
    assertEquals("Account not found", exception.getMessage());
    verify(accountRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("계좌가 존재하지 않을 때 deposit() 호출 시 예외 발생")
  void depositAccountNotFound() {
    // given
    when(accountRepository.findById(1L)).thenReturn(Optional.empty());

    // when & then
    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> balanceService.deposit(1L, 500L));
    assertEquals("Account not found", exception.getMessage());
    verify(accountRepository, times(1)).findById(1L);
  }

  @Test
  @DisplayName("계좌가 존재하지 않을 때 withdraw() 호출 시 예외 발생")
  void withdrawAccountNotFound() {
    // given
    when(accountRepository.findById(1L)).thenReturn(Optional.empty());

    // when & then
    RuntimeException exception = assertThrows(RuntimeException.class,
        () -> balanceService.withdraw(1L, 500L));
    assertEquals("Account not found", exception.getMessage());
    verify(accountRepository, times(1)).findById(1L);
  }
}
