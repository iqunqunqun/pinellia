package com.ivan.pinellia.core.tool.utils.thread;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * <p></p>
 *
 * @author chenyf
 * @className TestMethod
 * @since 2020/10/28 14:18
 */
@Slf4j
public class TestMethod {
    @SneakyThrows
    public static void main(String[] args) {

        CountDownLatch downLatch = new CountDownLatch(30);

        AtomicInteger integer = new AtomicInteger(0);

        AtomicReference<List<String>> atomicReference = new AtomicReference<>(Lists.newArrayList());

        ThreadPoolConfig poolConfig = new ThreadPoolConfig();
        ThreadPoolExecutor userThreadPool = poolConfig.userThreadPool();

        for (int i = 0; i < 30; i++) {
            UserThread userThread = new UserThread(downLatch, integer, atomicReference);
            userThreadPool.submit(userThread);
        }

        downLatch.await();

        log.info("任务总量 - {}", userThreadPool.getTaskCount());

        log.info("完成 - {}", userThreadPool.getCompletedTaskCount());

        log.info("当前运行 - {}", userThreadPool.getActiveCount());

        System.out.println("integer = " + integer);

        System.out.println("integer1 = " + atomicReference.toString());

        System.out.println("atomicReferenceList = " + atomicReference.get().size());

        log.info(String.valueOf(userThreadPool.isTerminated()));

        Map<String, Object> objectMap = Util.getMap();

        ConcurrentHashMap<String, Object> conMap = Util.getConMap();

        log.info("map - size - {}", objectMap.size());

        log.info("conMap - size - {}", conMap.size());

        userThreadPool.shutdown();
    }
}
