package com.ivan.pinellia.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.ivan.pinellia.LoginUser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 *
 * @author ivan
 * @className RequestUtil
 * @since 2020/11/15 21:03
 */

@Component
public class RequestUtil {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    private AuthenticationManager authenticationManager;

    @SneakyThrows
    public OAuth2AccessToken token(LoginUser loginUser) {

        Map<String, Object> map = BeanUtil.beanToMap(loginUser, true, true);

        Map<String, String> objectMap = MapUtil.createMap(HashMap.class);

        for (String key : map.keySet()) {
            objectMap.put(key, String.valueOf(map.get(key)));
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getClientId(), null, null);

        OAuth2AccessToken auth2AccessToken = tokenEndpoint.postAccessToken(authenticationToken, objectMap).getBody();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return auth2AccessToken;
    }












}
