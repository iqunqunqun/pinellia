package com.ivan.pinellia.exception;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.api.ResultCode;
import com.ivan.pinellia.tool.json.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * <p></p>
 *
 * @author ivan
 * @className PinelliaAuthenticationEntryPoint
 * @since 2020/11/16 20:19
 */

@Slf4j
@Component
public class PinelliaAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JsonUtil.toJson(R.fail(ResultCode.UN_AUTHORIZED)));
    }
}
