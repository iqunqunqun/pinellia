package com.ivan.pinellia.config;

import com.ivan.pinellia.constant.SecurityConstants;
import com.ivan.pinellia.service.PineliiaUserDetailServiceImpl;
import com.ivan.pinellia.service.PinelliaClientDetailsServiceImpl;
import com.ivan.pinellia.service.PinelliaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author chenyf
 * @className AuthorizationServerConfig
 * @since 2020/10/30 17:08
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PinelliaClientDetailsServiceImpl clientDetailsService;

    @Autowired
    private PineliiaUserDetailServiceImpl userDetailService;

    @Autowired
    private RedisTokenStore tokenStore;

    @Autowired
    private TokenEnhancer tokenEnhancer;

    @Autowired
    private AuthorizationServerTokenServices tokenService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenServices(tokenService)
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore)
                .tokenEnhancer(tokenEnhancer)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // refresh token有两种使用方式：重复使用(true)、非重复使用(false)，默认为true
                //      1 重复使用：access token过期刷新时， refresh token过期时间未改变，仍以初次生成的时间为准
                //      2 非重复使用：access token过期刷新时， refresh token过期时间延续，在refresh token有效期内刷新便永不失效达到无需再次登录的目的
                .reuseRefreshTokens(false);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setAccessTokenValiditySeconds(1);
        tokenServices.setRefreshTokenValiditySeconds(1);
        tokenServices.setSupportRefreshToken(true);

        return tokenServices;
    }


    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> additionalInfo = new HashMap<>(4);

            PinelliaUser pinelliaUser = (PinelliaUser) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put(SecurityConstants.DETAILS_USER_ID, pinelliaUser.getUserId());
            additionalInfo.put(SecurityConstants.DETAILS_USERNAME, pinelliaUser.getUsername());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("pinellia");
        System.out.println("encode = " + encode);
    }

}