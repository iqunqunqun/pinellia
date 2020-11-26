package com.ivan.pinellia;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * <p></p>
 *
 * @author ivan
 * @className FlowApplication
 * @since 2020/11/26 15:57
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class FlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowApplication.class, args);
    }

}
