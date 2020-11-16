package com.ivan.pinellia.config;

import com.ivan.pinellia.provider.SkipUrlProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * <p></p>
 *
 * @author ivan
 * @className ResourceConfig
 * @since 2020/11/14 12:16
 */

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class ResourceConfig {

    @Autowired
    private SkipUrlProvider skipUrlProvider;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http.authorizeExchange()
                .pathMatchers(skipUrlProvider.getSkipUrlList().toArray(new String[0])).permitAll()
                .and()
                .authorizeExchange().anyExchange().permitAll()
                .and()
                .csrf().disable().build();
    }

}
