package com.ivan.pinellia.tool.redis.lock;

/**
 * <p></p>
 *
 * @author ivan
 * @className LockHandler
 * @since 2020/12/2 17:51
 */
@FunctionalInterface
public interface LockHandler<T> {

    /**
     * the logic you want to execute
     *
     * @author piaoruiqing
     *
     * @return
     * @throws Throwable
     */
    T handle() throws Throwable;
}
