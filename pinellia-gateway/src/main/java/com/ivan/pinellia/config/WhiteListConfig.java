package com.ivan.pinellia.config;

/**
 * <p></p>
 *
 * @author chenyf
 * @className WhiteListConfig
 * @since 2020/11/13 10:19
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 白名单配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "whitelist")
public class WhiteListConfig {

    private List<String> urls;

}
