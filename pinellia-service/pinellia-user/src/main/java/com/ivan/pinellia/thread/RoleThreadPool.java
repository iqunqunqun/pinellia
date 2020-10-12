package com.ivan.pinellia.thread;

import cn.hutool.core.util.RandomUtil;
import com.ivan.pinellia.dto.UserDTO;
import com.netflix.hystrix.*;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author ivan
 * @className RoleThreadPool
 * @since 2020/9/15 22:27
 */

@Slf4j
public class RoleThreadPool extends HystrixCommand<String> {

    private UserDTO userDTO;

    public RoleThreadPool(UserDTO userDTO) {
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("roleGroup"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("rolePool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withMaximumSize(5)
                        .withKeepAliveTimeMinutes(1)
                        .withMaxQueueSize(1)
                        .withQueueSizeRejectionThreshold(1))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        );

        this.userDTO = userDTO;

    }

    @Override
    protected String run() throws Exception {

        TimeUnit.MILLISECONDS.sleep(100);

        userDTO.getArrayList().add(String.valueOf(RandomUtil.randomInt(1,5)));

        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

        return userDTO.getArrayList().toString();
    }
}
