package com.ivan.pinellia.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONException;
import com.ivan.pinellia.constant.SecurityConstants;
import com.ivan.pinellia.tool.api.R;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * <p></p>
 *
 * @author ivan
 * @className TokenController
 * @since 2020/11/9 23:02
 */
@Slf4j
@AllArgsConstructor
@RestController
public class TokenController {

    @Autowired
    private RedisTokenStore redisTokenStore;

    @SneakyThrows
    @DeleteMapping("/token/logout")
    public R logout(HttpServletRequest request) {

        String token = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);

        String jwt = token.replace(SecurityConstants.JWT_TOKEN_PREFIX, StrUtil.EMPTY);

        OAuth2Authentication authentication = redisTokenStore.readAuthentication(jwt);

        redisTokenStore.removeAccessToken(jwt);

        return R.data(jwt);
    }


}
