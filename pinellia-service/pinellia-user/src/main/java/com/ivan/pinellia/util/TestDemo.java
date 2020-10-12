package com.ivan.pinellia.util;

import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;
import lombok.SneakyThrows;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p></p>
 *
 * @author ivan
 * @className TestDemo
 * @since 2020/7/22 22:58
 */
public class TestDemo {

    public static int i = 0;

    public static void main(String[] args) {


        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }


}
