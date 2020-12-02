package com.ivan.pinellia.controller;

import cn.hutool.core.util.StrUtil;
import com.ivan.pinellia.model.LoginUser;
import com.ivan.pinellia.constant.SecurityConstants;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.util.RequestUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Map;

/**
 * <p></p>
 *
 * @author ivan
 * @className TokenController
 * @since 2020/11/9 23:02
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth")
public class TokenController {

    @Autowired
    private RedisTokenStore redisTokenStore;

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private RequestUtil requestUtil;

    @SneakyThrows
    @PostMapping("/login")
    public R<OAuth2AccessToken> login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginUser loginUser) {
        OAuth2AccessToken token = requestUtil.token(loginUser);
        return R.data(token);
    }


    @PostMapping("/token")
    @ApiOperation(value = "用户登录Post", notes = "用户登录Post")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grant_type", required = true, value = "授权类型", paramType = "form"),
            @ApiImplicitParam(name = "username", required = false, value = "用户名", paramType = "form"),
            @ApiImplicitParam(name = "password", required = false, value = "密码", paramType = "form"),
            @ApiImplicitParam(name = "scope", required = true, value = "使用范围", paramType = "form"),
    })
    public R<?> postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return R.data(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    @SneakyThrows
    @DeleteMapping("/logout")
    public R logout(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.JWT_TOKEN_HEADER);
        String jwt = token.replace(SecurityConstants.JWT_TOKEN_PREFIX, StrUtil.EMPTY);
        redisTokenStore.removeAccessToken(jwt);
        return R.data(jwt);
    }

    @GetMapping("/test")
    public R test(@RequestParam String token) {
        OAuth2Authentication oAuth2Authentication = redisTokenStore.readAuthentication(token);

        OAuth2AccessToken oAuth2AccessToken = redisTokenStore.readAccessToken(token);

        return R.data("authentication");
    }

}
