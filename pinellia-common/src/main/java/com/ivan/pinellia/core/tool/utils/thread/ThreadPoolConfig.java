package com.ivan.pinellia.core.tool.utils.thread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author chenyf
 * @className ThreadPoolConfig
 * @since 2020/10/28 13:32
 */

@Configuration
public class ThreadPoolConfig {


    @Bean("userThreadPool")
    public ThreadPoolExecutor userThreadPool() {

        int CORE_POOL_SIZE = 20;

        int MAX_POOL_SIZE = 30;

        long KEEP_TIME = 30L;

        ThreadFactory threadFactory = ThreadFactoryBuilder.create().setNamePrefix("test").build();

        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100), threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }

}
