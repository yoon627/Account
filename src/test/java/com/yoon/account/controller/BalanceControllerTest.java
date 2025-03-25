package com.yoon.account.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.account.domain.Account;
import com.yoon.account.dto.DepositRequest;
import com.yoon.account.service.BalanceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(BalanceController.class)
class BalanceControllerTest {

  @MockitoBean
  private BalanceService balanceService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void getBalanceSuccess() throws Exception {
    //given
    Long accountId = 1L;
    given(balanceService.getBalance(accountId)).willReturn(1000L);
    //when & then
    mockMvc.perform(get("/account/{accountId}/balance", accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(
                Account.builder().build()
            )))
        .andExpect((status().isOk()))
        .andExpect(jsonPath("$").value(1000L))
        .andDo(print());
    verify(balanceService).getBalance(accountId);
  }

  @Test
  void depositSuccess() throws Exception {
    // given
    Long accountId = 1L;
    Long amount = 1000L;
    given(balanceService.deposit(accountId, amount)).willReturn(1000L);

    // when & then
    mockMvc.perform(post("/account/{id}/deposit", accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(
                DepositRequest.builder().amount(1000L).build()
            )))
        .andExpect(status().isOk())  // HTTP 200 응답 확인
        .andExpect(jsonPath("$").value(1000L))
        .andDo(print());
    verify(balanceService).deposit(accountId, amount);
  }

  @Test
  void withdrawSuccess() throws Exception {
    // given
    Long accountId = 1L;
    Long amount = 1000L;
    given(balanceService.withdraw(accountId, amount)).willReturn(0L);

    // when & then
    mockMvc.perform(post("/account/{id}/withdraw", accountId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(
                DepositRequest.builder().amount(amount).build()
            )))
        .andExpect(status().isOk())  // HTTP 200 응답 확인
        .andExpect(jsonPath("$").value(0L))
        .andDo(print());
    verify(balanceService).withdraw(accountId, amount);
  }
}
