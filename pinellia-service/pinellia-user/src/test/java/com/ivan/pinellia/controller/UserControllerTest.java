package com.ivan.pinellia.controller;

import com.ivan.pinellia.UserApplication;
import com.ivan.pinellia.tool.redis.core.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

@SpringBootTest(classes = {UserApplication.class})
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void detail() {
        Set<String> set = redisService.scan("key");

        System.out.println("set.size() = " + set.size());

    }
}