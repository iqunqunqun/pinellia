package com.ivan.pinellia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p></p>
 *
 * @author chen
 * @className UserApplication
 * @since 2020/5/4 18:32
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ivan.pinellia.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
