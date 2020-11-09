package com.ivan.pinellia.tool.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author ivan
 * @className RedisConfigurationProperties
 * @since 2020/8/26 16:05
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisConfigurationProperties {

    private List<String> nodes = new ArrayList<>();

    public List<String> getNodes() {
        return nodes;
    }

    public void setNodes(List<String> nodes) {
        this.nodes = nodes;
    }
}
