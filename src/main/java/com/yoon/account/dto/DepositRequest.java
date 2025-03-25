package com.yoon.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DepositRequest {

  private Long amount;
}
