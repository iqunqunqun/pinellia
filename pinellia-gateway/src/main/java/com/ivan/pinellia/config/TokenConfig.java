package com.ivan.pinellia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * <p></p>
 *
 * @author chenyf
 * @className TokenConfig
 * @since 2020/11/12 10:50
 */
@Configuration
public class TokenConfig {

    @Bean
    public TokenStore tokenStore() {

        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());

        return jwtTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey("auth");
        return tokenConverter;
    }

}
