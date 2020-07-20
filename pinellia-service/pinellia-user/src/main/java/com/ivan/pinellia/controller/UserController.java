package com.ivan.pinellia.controller;


import com.ivan.pinellia.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ivan
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

}

