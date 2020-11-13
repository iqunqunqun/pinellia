package com.ivan.pinellia.config;

import com.ivan.pinellia.constant.CacheConstant;
import com.ivan.pinellia.service.PineliiaUserDetailServiceImpl;
import com.ivan.pinellia.service.PinelliaClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;

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
