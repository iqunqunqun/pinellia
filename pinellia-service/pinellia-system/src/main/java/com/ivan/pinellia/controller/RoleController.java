package com.ivan.pinellia.controller;


import com.ivan.pinellia.core.tool.api.R;
import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.entity.User;
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

    @PostMapping("/submit")
    public R submit(@RequestBody Role role) {
        return R.status(this.roleService.saveOrUpdate(role));
    }

}

