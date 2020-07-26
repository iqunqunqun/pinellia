package com.ivan.pinellia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p></p>
 *
 * @author ivan
 * @className SystemApplication
 * @since 2020/7/26 11:46
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.ivan.pinellia.mapper")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
