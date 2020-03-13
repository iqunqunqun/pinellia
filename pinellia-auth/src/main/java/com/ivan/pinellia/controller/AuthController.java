package com.ivan.pinellia.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivan.pinellia.dto.LoginDTO;
import com.ivan.pinellia.feign.IUserClient;
import com.ivan.pinellia.query.UserQuery;
import com.ivan.pinellia.secure.config.WebSecurityConfig;
import com.ivan.pinellia.secure.provider.CustomUserDetailsServiceImpl;
import com.ivan.pinellia.secure.utils.JwtTokenUtils;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.api.ResultCode;
import com.ivan.pinellia.vo.UserVO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author ivan
 */
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private IUserClient userClient;


    @ApiOperation("登录接口")
    @ApiOperationSupport(order = 1)
    @PostMapping("/login")
    public R login(@Valid @RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        // 通过用户名和密码创建一个 Authentication 认证对象，实现类为 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());

        try {
            //通过 AuthenticationManager（默认实现为ProviderManager）的authenticate方法验证 Authentication 对象
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            //将 Authentication 绑定到 SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //生成Token
            String token = jwtTokenUtils.createToken(authentication);
            //将Token写入到Http头部
            response.addHeader(WebSecurityConfig.AUTHORIZATION_HEADER, "Bearer " + token);
            return R.data("Bearer " + token, "登陆成功");
        } catch (BadCredentialsException authentication) {
            return R.fail(ResultCode.UN_AUTHORIZED.getCode(), authentication.getMessage());
        }
    }

    @GetMapping("/info")
    public R getInfo(UserQuery query) {
        R<UserVO> admin = userClient.detailByAccount("admin");
        return R.data(admin);
    }

}
