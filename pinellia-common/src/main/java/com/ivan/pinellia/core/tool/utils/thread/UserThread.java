package com.ivan.pinellia.core.tool.utils.thread;

import cn.hutool.core.util.RandomUtil;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p></p>
 *
 * @author chenyf
 * @className UserThread
 * @since 2020/10/28 13:30
 */
public class UserThread implements Runnable {

    private CountDownLatch downLatch;

    private AtomicInteger number;

    private AtomicReference<List<String>> unboxInteger;

    public UserThread(CountDownLatch downLatch, AtomicInteger number, AtomicReference<List<String>> integer1) {
        this.downLatch = downLatch;
        this.number = number;
        this.unboxInteger = integer1;
    }

    @Override
    public void run() {
        downLatch.countDown();
        number.getAndIncrement();
        System.out.println("AtomicInteger = " + number);
        unboxInteger.get().add(RandomUtil.randomString(6));

        Util.getMap().put(Thread.currentThread().getName() + RandomUtil.randomString(2), Thread.currentThread());

        Util.getConMap().put(Thread.currentThread().getName() + RandomUtil.randomString(2), Thread.currentThread());

    }
}
