package com.ivan.pinellia.core.tool.redis;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * <p></p>
 *
 * @author ivan
 * @className RedisConfig
 * @since 2020/8/26 16:05
 */
@Configuration
public class RedisConfig {

    @Autowired
    private RedisConfigurationProperties redisConfigurationProperties;

    @Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> nodeSet = new HashSet<>();
        for(String node :redisConfigurationProperties.getNodes()) {
            String[] split = node.split(StrUtil.COLON);
            String host = split[0];
            int port = Integer.parseInt(split[1]);
            nodeSet.add(new HostAndPort(host, port));
        }

        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000);
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);

        return new JedisCluster(nodeSet, jedisPoolConfig);
    }

}

