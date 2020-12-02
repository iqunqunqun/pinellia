package com.ivan.pinellia.tool.redis.lock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author ivan
 * @className DistributedLock
 * @since 2020/12/2 17:42
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributedLock {

    /**
     * 分布式锁名称
     *
     * @return String
     */
    String key() default "distributed-lock-redisson";

    /**
     * 分布式锁的值（参数-param 线程ID-thread）
     * @return
     */
    String type() default "thread";

    /**
     * 需要去除的字段
     */
    String[] excludeFields() default {};

    /**
     * 锁超时时间,默认十秒
     *
     * @return int
     */
    int expireSeconds() default 10;
}