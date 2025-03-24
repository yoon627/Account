package com.yoon.account.domain;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Account extends BaseEntity {

  private long balance;

  public void deposit(long amount) {
    if (amount < 0) {
      throw new RuntimeException("Amount must be positive");
    }
    balance += amount;
  }

  public void withdraw(long amount) {
    if (amount > balance) {
      throw new RuntimeException("Amount exceeds balance");
    }
    balance -= amount;
  }

}
