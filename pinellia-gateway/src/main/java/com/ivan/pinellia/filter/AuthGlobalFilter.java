package com.ivan.pinellia.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ivan.pinellia.constant.SecurityConstants;
import com.ivan.pinellia.provider.SkipUrlProvider;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.api.ResultCode;
import com.ivan.pinellia.tool.json.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

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

    @Autowired
    private SkipUrlProvider skipUrlProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpResponse resp = exchange.getResponse();

        // 请求路径
        String path = exchange.getRequest().getURI().getPath();

        // Bearer Token
        String bearerToken = exchange.getRequest().getHeaders().getFirst(SecurityConstants.JWT_TOKEN_HEADER);

        log.info("******************************************************");
        log.info("【uri】 : {} ", exchange.getRequest().getURI());
        log.info("【path】 : {} ", path);
        log.info("【Bearer Token】 : {} ",bearerToken);
        log.info("******************************************************");

        // 1. 验证是不是登录请求，不是登录请求需要过滤
        if (isSkip(path)) {
            return chain.filter(exchange);
        }

        // 2. 验证请求是否携带token
        if (StrUtil.isBlank(bearerToken)) {
            return unAuth(resp, "缺失令牌，验证失败");
        }

        // 3. 验证token
        if (!isAuth(bearerToken)) {
            return unAuth(resp, "令牌验证失败");
        }

        return chain.filter(exchange);
    }

    /**
     * 认证工作
     * @param bearerToken
     * @return
     */
    private boolean isAuth(String bearerToken) {

        String token = StrUtil.replace(bearerToken, SecurityConstants.JWT_TOKEN_PREFIX, StrUtil.EMPTY);

        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        return ObjectUtil.isNotNull(oAuth2AccessToken);
    }

    /**
     * 认证不成功则直接返回
     * @param resp
     * @param msg
     * @return
     */
    @SneakyThrows
    private Mono<Void> unAuth(ServerHttpResponse resp, String msg) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] result = JsonUtil.toJsonAsBytes(R.fail(msg));
        DataBuffer buffer = resp.bufferFactory().wrap(result);
        return resp.writeWith(Flux.just(buffer));
    }

    /**
     * 过滤不需要鉴权的URL
     * @return
     * @param path
     */
    private boolean isSkip(String path) {

        List<String> skipUrlList = skipUrlProvider.getSkipUrlList();

        return skipUrlList
                .stream()
                .map(url -> url.replace(SkipUrlProvider.TARGET, SkipUrlProvider.REPLACEMENT))
                .anyMatch(path::contains);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}