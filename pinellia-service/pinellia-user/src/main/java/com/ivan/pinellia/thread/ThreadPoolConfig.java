package com.ivan.pinellia.thread;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author ivan
 * @className ThreadPoolConfig
 * @since 2020/9/15 21:26
 */

@Configuration
public class ThreadPoolConfig {

    private static final Integer  CORE_POOL_SIZE = 10;

    private static final Integer  MAX_POOL_SIZE = 11;

    private static final Long  KEEP_ALIVE_TIME = 2L;

    @Bean(value = "rolePool")
    public ExecutorService initRoleThreadPool() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<>(300), new ThreadPoolExecutor.CallerRunsPolicy());
    }



}
