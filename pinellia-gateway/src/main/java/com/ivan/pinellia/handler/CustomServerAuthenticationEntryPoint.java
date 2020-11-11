//package com.ivan.pinellia.handler;
//
//import cn.hutool.json.JSONUtil;
//import com.ivan.pinellia.tool.api.R;
//import com.ivan.pinellia.tool.api.ResultCode;
//import org.springframework.core.io.buffer.DataBuffer;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import java.nio.charset.Charset;
//
///**
// * <p></p>
// *
// * @author chenyf
// * @className CustomServerAuthenticationEntryPoint
// * @since 2020/11/11 15:02
// */
//@Component
//public class CustomServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
//
//    @Override
//    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
//        ServerHttpResponse response = exchange.getResponse();
//        response.setStatusCode(HttpStatus.OK);
//        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//        response.getHeaders().set("Access-Control-Allow-Origin", "*");
//        response.getHeaders().set("Cache-Control", "no-cache");
//        String body = JSONUtil.toJsonStr(R.fail(ResultCode.UN_AUTHORIZED));
//        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
//        return response.writeWith(Mono.just(buffer));
//    }
//
//}
