package com.ivan.pinellia.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.ivan.pinellia.entity.Role;
import com.ivan.pinellia.entity.User;
import com.ivan.pinellia.mybatis.support.Condition;
import com.ivan.pinellia.service.IRoleService;
import com.ivan.pinellia.tool.api.R;
import com.ivan.pinellia.tool.node.INode;
import com.ivan.pinellia.tool.utils.Func;
import com.ivan.pinellia.vo.RoleVO;
import com.ivan.pinellia.wrapper.RoleWrapper;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ivan
 * @since 2020-01-12
 */
@Api(value = "角色", tags = "角色")
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private IRoleService roleService;

    /**
     * 详情
     */
    @GetMapping("/detail/{roleId}")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "详情", notes = "传入role")
    public R<RoleVO> detail(@PathVariable("roleId") Integer roleId) {
        Role detail = roleService.getOne(Condition.getQueryWrapper(new Role()).lambda().eq(Role::getRoleId, roleId));
        return R.data(RoleWrapper.build().entityVO(detail));
    }


    /**
     * 获取角色树形结构
     */
    @GetMapping("/tree")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "树形结构", notes = "树形结构")
    public R<List<RoleVO>> tree() {
        List<RoleVO> tree = roleService.tree();
        return R.data(tree);
    }

    /**
     * 新增或修改
     */
    @PostMapping("/submit")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增或修改", notes = "传入roleId")
    public R submit(@Valid @RequestBody Role role) {
        return R.status(roleService.saveOrUpdate(role));
    }


    /**
     * 删除
     */
    @PostMapping("/remove")
    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "删除", notes = "传入ids")
    public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
        return R.status(roleService.removeByIds(Func.toIntList(ids)));
    }

    /**
     * 设置菜单权限
     *
     * @param roleIds:
     * @param menuIds:
     * @return
     */
    @PostMapping("/grant")
    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "权限设置", notes = "传入roleId集合以及menuId集合")
    public R grant(@ApiParam(value = "roleId集合", required = true) @RequestParam String roleIds,
                   @ApiParam(value = "menuId集合", required = true) @RequestParam String menuIds) {
        boolean temp = roleService.grant(Func.toIntList(roleIds), Func.toIntList(menuIds));
        return R.status(temp);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "参数名称", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "roleAlias", value = "角色别名", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "query", dataType = "Integer"),
    })
    @ApiOperationSupport(order = 7)
    @ApiOperation(value = "列表", notes = "传入role")
    public R<List<INode>> list(@ApiIgnore @RequestParam(value = "roleId", required = false) Integer roleId) {
        List<Role> list = roleService.list();
        return R.data(RoleWrapper.build().listNodeVO(list));
    }
}

