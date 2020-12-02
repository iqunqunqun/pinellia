package com.ivan.pinellia.tool.redis.lock;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import com.ivan.pinellia.tool.redis.core.RedisService;
import com.ivan.pinellia.tool.utils.ReqDedupHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author ivan
 */
@Slf4j
@Aspect
@Component
public class DistributedLockableAspect {

    private static final String PARAM = "param";

    private static final String THREAD = "thread";

    @Autowired
    private RedisLock redisLock;

    @Pointcut(value = "execution(* *(..)) && @annotation(com.ivan.pinellia.tool.redis.lock.DistributedLock)")
    public void distributedLockable() {}

    /**
     * 切面环绕通知
     * @param joinPoint ProceedingJoinPoint
     * @param distributedLock DistributedLock
     * @return Object
     */
    @Around("distributedLockable() && @annotation(distributedLock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock distributedLock) {
        log.info("[开始]执行RedisLock环绕通知,获取Redis分布式锁开始");
        // 获取锁名称
        String lockName = distributedLock.key();
        // 获取超时时间
        int expireSeconds = distributedLock.expireSeconds();
        // 需去除的字段
        String[] excludeFields = distributedLock.excludeFields();
        // 参数
        String type = distributedLock.type();

        String value = String.valueOf(Thread.currentThread().getId());

        Object[] joinPointArgs = joinPoint.getArgs();

        String key = lockName + StrUtil.COLON + type;

        if (type.equalsIgnoreCase(PARAM)) {
            value = "";
        }

        if (redisLock.lock(key, value, expireSeconds)) {
            try {
                log.info("获取Redis分布式锁[成功]，加锁完成，开始执行业务逻辑...");
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                log.error("获取Redis分布式锁[异常]，加锁失败", throwable);
                throwable.printStackTrace();
            } finally {
                redisLock.release(key, value);
                log.info("释放Redis分布式锁[成功]，解锁完成，结束业务逻辑...");
            }
        } else {
            log.error("获取Redis分布式锁[失败]");
        }
        log.info("[结束]执行RedisLock环绕通知");
        return null;
    }

}
