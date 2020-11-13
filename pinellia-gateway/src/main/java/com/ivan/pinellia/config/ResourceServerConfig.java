package com.ivan.pinellia.config;

import cn.hutool.core.util.ArrayUtil;
import com.ivan.pinellia.component.CustomServerAccessDeniedHandler;
import com.ivan.pinellia.component.CustomServerAuthenticationEntryPoint;
import com.ivan.pinellia.constant.SecurityConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * <p></p>
 *
 * @author chenyf
 * @className ResourceServerConfig
 * @since 2020/11/12 13:53
 */

@AllArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {

        @Bean
        public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {

            return http.authorizeExchange()
                    .pathMatchers("/auth/**").permitAll()
                    .and()
                    .authorizeExchange().anyExchange().authenticated()
                    .and().csrf().disable().build();
        }


}

