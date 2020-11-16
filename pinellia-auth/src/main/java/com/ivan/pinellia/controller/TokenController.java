package com.ivan.pinellia.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapProxy;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONException;
import com.ivan.pinellia.LoginUser;
import com.ivan.pinellia.constant.SecurityConstants;
import com.ivan.pinellia.service.PineliiaUserDetailServiceImpl;
import com.ivan.pinellia.service.PinelliaUser;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.util.RequestUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
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
@RequestMapping("/token")
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
