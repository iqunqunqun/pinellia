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
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
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
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private CheckTokenEndpoint checkTokenEndpoint;

    @Autowired
    private TokenStore tokenStore;

    @SneakyThrows
    @GetMapping("/check")
    public R check(@RequestParam String token) {

        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);

        if (oAuth2AccessToken == null) {
            throw new InvalidTokenException("Token was not recognised");
        }

        if (oAuth2AccessToken.isExpired()) {
            throw new InvalidTokenException("Token has expired");
        }

        log.info(oAuth2AccessToken.getValue());

        return R.data(checkTokenEndpoint.checkToken(oAuth2AccessToken.getValue()));
    }

    @SneakyThrows
    @DeleteMapping("/logout")
    public R logout(HttpServletRequest request) {

        String token = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);

        String jwt = token.replace(SecurityConstants.JWT_TOKEN_PREFIX, StrUtil.EMPTY);

        OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(jwt);

        tokenStore.removeAccessToken(auth2AccessToken);

        return R.data(checkTokenEndpoint.checkToken(token));
    }


}
