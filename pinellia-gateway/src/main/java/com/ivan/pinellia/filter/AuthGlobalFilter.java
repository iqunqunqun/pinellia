package com.ivan.pinellia.filter;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.ivan.pinellia.constant.SecurityConstants;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.api.ResultCode;
import com.nimbusds.jose.JWSObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author chenyf
 * @className AuthGlobalFilter
 * @since 2020/11/13 10:34
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {


    @Autowired
    private RedisTokenStore tokenStore;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(SecurityConstants.JWT_TOKEN_HEADER);

        log.info("================================");
        log.info("uri-{}", exchange.getRequest().getURI());
        log.info("token-{}",token);
        log.info("================================");
        if (StrUtil.isBlank(token)) {
            return chain.filter(exchange);
        }

        token = token.replace(SecurityConstants.JWT_TOKEN_PREFIX, Strings.EMPTY);

        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);

        System.out.println("oAuth2AccessToken = " + oAuth2AccessToken);

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}