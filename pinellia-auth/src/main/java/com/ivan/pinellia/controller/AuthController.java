package com.ivan.pinellia.controller;

import com.ivan.pinellia.core.tool.api.R;
import com.ivan.pinellia.feign.IUserClient;
import com.ivan.pinellia.security.jwt.JWTFilter;
import com.ivan.pinellia.security.jwt.TokenProvider;
import com.ivan.pinellia.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p></p>
 *
 * @author chenyf
 * @className AuthController
 * @since 2020/10/30 13:47
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserClient userClient;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/authenticate")
    public R<Object> authorize(@Valid @RequestBody UserVO loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean rememberMe = (loginDto.getIsRememberMe() == null) ? false : loginDto.getIsRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return R.data(httpHeaders, jwt);
    }

}
