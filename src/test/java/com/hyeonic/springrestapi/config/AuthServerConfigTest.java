package com.hyeonic.springrestapi.config;

import com.hyeonic.springrestapi.accounts.Account;
import com.hyeonic.springrestapi.accounts.AccountRole;
import com.hyeonic.springrestapi.accounts.AccountService;
import com.hyeonic.springrestapi.common.BaseControllerTest;
import com.hyeonic.springrestapi.common.TestDecription;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Test
    @TestDecription("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {
        // Given
        String username = "keeun@email.com";
        String password = "keesun";
        Account keesun = Account.builder()
                .email(username)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();
        this.accountService.saveAccount(keesun);

        String clientId = "myApp";
        String clientSecret = "pass";

        this.mockMvc.perform(post("/oauth/token")
                .param("username", username)
                .param("password", password)
                .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());
    }

}