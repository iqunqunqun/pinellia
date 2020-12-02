package com.ivan.pinellia.tool.redis.lock;

import com.ivan.pinellia.tool.redis.core.RedisService;
import com.ivan.pinellia.tool.utils.ReqDedupHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author ivan
 * @className RedisLock
 * @since 2020/12/2 18:10
 */

@Slf4j
@Data
@Component
public class RedisLock {

    @Autowired
    private RedisService redisService;

    /**
     * 加锁操作
     *
     * @return boolean
     */
    public boolean lock(String lockName, long expireSeconds) {
        boolean nx = redisService.setNx(lockName, String.valueOf(Thread.currentThread().getId()), expireSeconds, TimeUnit.SECONDS);
        try {
            if (nx) {
                log.info("获取Redisson分布式锁[成功],lockName={}", lockName);
            } else {
                log.info("获取Redisson分布式锁[失败],lockName={}", lockName);
            }
        } catch (Exception e) {
            log.error("获取Redisson分布式锁[异常]，lockName=" + lockName, e);
            e.printStackTrace();
            return false;
        }
        return nx;
    }

    /**
     * 加锁操作
     *
     * @return boolean
     */
    public boolean lock(String lockName, String value, long expireSeconds) {

        boolean nx = redisService.setNx(lockName, value, expireSeconds, TimeUnit.SECONDS);
        try {
            if (nx) {
                log.info("获取Redisson分布式锁[成功],lockName={}", lockName);
            } else {
                log.info("获取Redisson分布式锁[失败],lockName={}", lockName);
            }
        } catch (Exception e) {
            log.error("获取Redisson分布式锁[异常]，lockName=" + lockName, e);
            e.printStackTrace();
            return false;
        }
        return nx;
    }

    /**
     * 解锁
     *
     * @param lockName 锁名称
     */
    public void release(String lockName, String value) {
        redisService.release(lockName, value);
    }

    /**
     * 解锁
     *
     * @param lockName 锁名称
     */
    public void release(String lockName) {
        redisService.release(lockName, String.valueOf(Thread.currentThread().getId()));
    }

}
