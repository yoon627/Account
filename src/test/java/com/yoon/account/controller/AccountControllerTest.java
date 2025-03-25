package com.yoon.account.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yoon.account.domain.Account;
import com.yoon.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

  @MockitoBean
  private AccountService accountService;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void createAccountSuccess() throws Exception {
    //given
    given(accountService.createAccount())
        .willReturn(
            Account.builder()
                .id(1L)
                .balance(0L)
                .build());
    //when & then
    mockMvc.perform(post("/account")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(
                Account.builder().build()
            )))
        .andExpect((status().isOk()))
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.balance").value(0L))
        .andDo(print());
    verify(accountService).createAccount();
  }

  @Test
  void deleteAccountSuccess() throws Exception {
    //given
    long accountId = 1L;
    willDoNothing().given(accountService).deleteAccount(accountId);
    //when & then
    mockMvc.perform(delete("/account/{id}", accountId))
        .andExpect(status().isOk())
        .andDo(print());
    verify(accountService).deleteAccount(accountId);
  }
}
