package com.ivan.pinellia.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.ivan.pinellia.dto.UserDTO;
import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.feign.ISystemClient;
import com.ivan.pinellia.service.IUserService;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.api.ResultCode;
import com.ivan.pinellia.tool.api.annotation.CustomResponse;
import com.ivan.pinellia.tool.redis.lock.DistributedLock;
import com.ivan.pinellia.vo.UserInfo;
import com.ivan.pinellia.vo.UserVO;
import com.ivan.pinellia.wrapper.UserWrapper;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.locks.ReentrantLock;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ivan
 * @since 2020-05-04
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ISystemClient systemClient;

    @SneakyThrows
    @ApiOperation("新增用户")
    @PostMapping("/save")
    public R saveUser(@RequestBody UserDTO userDTO) {

        boolean saveFlag = this.userService.saveUser(userDTO);

        return R.data(saveFlag);
    }


    @ApiOperation("查询用户详情")
    @GetMapping("/detail/{id}")
    @PreAuthorize("@auth.hasAuth('ROLE_ADMIN')")
    @CustomResponse
    @DistributedLock(key = "user:detail")
    public UserVO detail(@PathVariable("id") Integer id) {
        User user = this.userService.detail(id);
        return UserWrapper.build().entityVO(user);
    }

    /**
     * 查询单条
     */
    @ApiOperation(value = "查看详情", notes = "传入id")
    @GetMapping("/userInfo")
    @CustomResponse
    public UserInfo userInfo(User user) {
        UserInfo userInfo = new UserInfo();
        User detail = userService.getOne(new QueryWrapper<>(user));

        if (ObjectUtil.isNotNull(detail)) {
            userInfo.setUser(detail);
            userInfo.setPermissions(new String[0]);
            userInfo.setRoles(new String[]{"ROLE_ADMIN"});
            return userInfo;
        }

        return userInfo;
    }


    @ApiOperation("测试分布式事务")
    @PostMapping("/test")
    public R testTransaction() {

        // 1.保存用户
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("测试用户");
        userDTO.setPassword("234");
        this.userService.saveUser(userDTO);

        // 2.保存角色
        Role role = new Role();
        role.setRoleName("测试角色");
        role.setRoleCode("test");
        this.systemClient.submit(role);
        return R.success(ResultCode.SUCCESS);
    }
}

