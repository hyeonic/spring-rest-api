package com.hyeonic.springrestapi.config;

import com.hyeonic.springrestapi.accounts.Account;
import com.hyeonic.springrestapi.accounts.AccountRole;
import com.hyeonic.springrestapi.accounts.AccountService;
import com.hyeonic.springrestapi.common.AppProperties;
import com.hyeonic.springrestapi.common.BaseControllerTest;
import com.hyeonic.springrestapi.common.TestDecription;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AppProperties appProperties;

    @Test
    @TestDecription("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {

        // Given
//        Account test = Account.builder()
//                .email( appProperties.getUserUsername() )
//                .password( appProperties.getUserPassword() )
//                .roles( Set.of( AccountRole.ADMIN, AccountRole.USER ) )
//                .build();
//
//        accountService.saveAccount( test );


        this.mockMvc.perform(post("/oauth/token")
                    .with(httpBasic( appProperties.getClientId(), appProperties.getClientSecret() ))
                    .param("username", appProperties.getUserUsername() )
                    .param("password", appProperties.getUserPassword() )
                    .param("grant_type", "password")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists());
    }

}