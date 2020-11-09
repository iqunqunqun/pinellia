package com.ivan.pinellia.controller;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author ivan
 * @since 2020-07-26
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @DS("master")
    @PostMapping("/submit")
    public R submit(@RequestBody Role role) {
        return R.status(this.roleService.saveOrUpdate(role));
    }

}

