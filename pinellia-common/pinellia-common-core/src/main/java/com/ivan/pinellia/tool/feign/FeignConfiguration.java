package com.ivan.pinellia.tool.feign;

import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author ivan
 * @className FeignConfig
 * @since 2020/11/15 17:44
 */
@Configuration
public class FeignConfiguration{
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("admin","123456");
    }
}
